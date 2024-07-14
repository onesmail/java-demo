package com.example.demo.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.AddUserDto;
import com.example.demo.entity.dto.PageResult;
import com.example.demo.entity.dto.UpdateUserDto;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 用户管理
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceLoader resourceLoader;

    private static void setResponse(HttpServletResponse response, String name) throws UnsupportedEncodingException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
    }

    /**
     * 添加用户信息。
     *
     * @param addUserDto 包含新用户信息的数据传输对象。
     * @return 返回添加用户操作的结果，添加成功后的数据库主键id
     */
    @ApiOperation("添加用户")
    @PostMapping("/adduser")
    public Long addUser(@RequestBody AddUserDto addUserDto) {
        return userService.addUser(addUserDto);
    }

    /**
     * 更新用户信息。
     *
     * @param updateUserDto 包含待更新用户信息的数据传输对象。
     * @return boolean 如果删除成功，则返回true；否则返回false。
     */
    @ApiOperation("更新用户")
    @PutMapping("/updateuser")
    public boolean updateUser(@RequestBody UpdateUserDto updateUserDto) {
        return userService.updateUser(updateUserDto);
    }

    /**
     * 根据用户ID删除用户。
     *
     * @param id 用户的唯一标识符。
     * @return 如果删除成功，则返回true；否则返回false。
     */
    @ApiOperation("删除用户")
    @DeleteMapping("/deleteUser/{id}")
    public boolean deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    /**
     * 获取所有用户信息
     *
     * @return 返回包含所有用户信息的列表
     */
    @ApiOperation("获取所有用户信息")
    @GetMapping("/getalluser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    /**
     * 分页获取用户信息。
     *
     * @param pageNum  当前页面的编号，用于指定要获取的数据页。默认值为1。
     * @param pageSize 每页显示的用户数量，用于控制每页的数据显示量。默认值为10。
     * @return PageResult 包含用户数据和分页信息的结果对象。
     */
    @ApiOperation("分页获取用户信息")
    @GetMapping("/getuserpage")
    public PageResult<User> getUserPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return userService.getUserPage(pageNum, pageSize);
    }

    /**
     * 根据页码和每页大小获取用户信息的分页结果（逻辑分页）。
     *
     * @param pageNum  当前页码，用于指定要获取的数据页。
     * @param pageSize 每页包含的用户数量，用于控制分页大小。
     * @return 返回包含用户信息的分页结果对象。
     */
    @ApiOperation("分页获取用户信息（逻辑分页）")
    @GetMapping("/queryuserpage")
    public PageResult<User> queryUserPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        return userService.queryUserPage(pageNum, pageSize);
    }

    /**
     * 根据用户姓名获取用户信息。
     *
     * @param username 用户姓名。
     * @return 用户信息。
     */
    @ApiOperation("根据用户姓名获取用户信息")
    @GetMapping("/getuserbyname")
    public List<User> getUserByName(@RequestParam(defaultValue = "") String username) {
        return userService.getUserByName(username);
    }

    /**
     * 根据用户姓名和年龄查询用户列表。
     *
     * @param username 用户的姓名，用于精确匹配用户。
     * @param age      用户的年龄，可选参数。如果指定了年龄，则只返回符合该年龄条件的用户。
     * @return 匹配条件的用户列表。如果找不到匹配的用户，返回空列表。
     */
    @ApiOperation("根据用户姓名和年龄查询用户列表。")
    @GetMapping("/getuserbynameandage")
    public List<User> getUserByNameAndAge(@RequestParam(defaultValue = "") String username, Integer age) {
        return userService.getUserByNameAndAge(username, age);
    }

    /**
     * 批量添加用户信息。
     *
     * @param entityList 要保存的用户信息列表。
     * @return 如果所有用户信息都保存成功，则返回true；否则返回false。
     */
    @ApiOperation("批量新增用户信息")
    @PostMapping("/adduserbatch")
    public boolean addUserBatch(@RequestBody Collection<AddUserDto> entityList) {
        return userService.addUserBatch(entityList);
    }

    /**
     * 通过上传Excel文件导入用户信息。
     *
     * @param file 上传的Excel文件，其中包含待添加的用户信息。
     * @return 如果用户信息添加成功，则返回true；否则返回false。
     * @throws IOException 如果读取文件流发生错误，则抛出此异常。
     */
    @ApiOperation("通过上传Excel文件导入用户信息")
    @PostMapping("/importexcel")
    public boolean importExcel(@RequestPart("file") MultipartFile file) throws IOException {
        List<AddUserDto> list = EasyExcel.read(file.getInputStream()).head(AddUserDto.class).sheet().doReadSync();

        return userService.addUserBatch(list);
    }

    /**
     * 导出用户信息到Excel。
     *
     * @throws IOException 如果读取文件流发生错误，则抛出此异常。
     */
    @ApiOperation("导出用户信息到Excel")
    @PostMapping("/exportexcel")
    public void exportExcel(HttpServletResponse response) throws IOException {

        setResponse(response, StrUtil.format("用户信息{}", DateUtil.format(new Date(), "yyyyMMddHHmmss")));

        List<User> list = userService.getAllUser();
        EasyExcel.write(response.getOutputStream()).head(User.class).excelType(ExcelTypeEnum.XLSX).sheet("下载用户信息").doWrite(list);
    }

    /**
     * 下载Excel模板文件。
     *
     * @param response HttpServletResponse对象，用于设置响应头和输出流。
     * @throws Exception 如果发生IO错误，则抛出此异常。
     */
    @ApiOperation("下载Excel模板文件")
    @GetMapping("/downloadexcel")
    public void downloadExcel(HttpServletResponse response) throws Exception {
        Resource resource = resourceLoader.getResource("classpath:template/用户信息.xlsx");

        File file = resource.getFile();
        InputStream inputStream = Files.newInputStream(file.toPath());
        OutputStream outputStream = response.getOutputStream();

        setResponse(response, "用户信息");

        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
    }

}
