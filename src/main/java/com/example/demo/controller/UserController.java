package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.AddUserDto;
import com.example.demo.entity.dto.PageResult;
import com.example.demo.entity.dto.UpdateUserDto;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * @description: 用户管理
 */
@Api(tags = "用户管理", description = "用户管理")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

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
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    /**
     * 分页获取用户信息。
     *
     * @param pageNum  当前页面的编号，用于指定要获取的数据页。默认值为1。
     * @param pageSize 每页显示的用户数量，用于控制每页的数据显示量。默认值为10。
     * @return PageResult 包含用户数据和分页信息的结果对象。
     */
    @ApiOperation("用户列表")
    @GetMapping("/getuser")
    public PageResult getUser(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize) {
        return userService.getUser(pageNum, pageSize);
    }

    /**
     * 根据用户姓名获取用户信息。
     *
     * @param username 用户姓名。
     * @return 用户信息。
     */
    @ApiOperation("根据用户姓名获取用户信息")
    @GetMapping("/{username}")
    public List<User> getUserByName(@RequestParam(defaultValue = "") String username) {
        return userService.getUserByName(username);
    }
}
