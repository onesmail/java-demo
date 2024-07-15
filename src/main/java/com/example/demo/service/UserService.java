package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.AddUserDto;
import com.example.demo.entity.dto.PageResult;
import com.example.demo.entity.dto.UpdateUserDto;

import java.util.Collection;
import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 添加用户信息
     *
     * @param addUserDto 包含用户添加信息的Dto（数据传输对象），用于传递用户相关数据到业务逻辑层。
     * @return Long 表示用户添加操作的结果，返回插入成功的主键id。
     */
    Long addUser(AddUserDto addUserDto);

    /**
     * 更新用户信息
     *
     * @param updateUserDto 包含待更新用户信息的数据传输对象。
     * @return 如果用户信息更新成功，则返回true；否则返回false。
     */
    boolean updateUser(UpdateUserDto updateUserDto);

    /**
     * 删除用户
     *
     * @param id 用户的ID，作为删除操作的唯一标识。
     * @return 如果成功删除用户，则返回true；否则返回false。
     */
    boolean deleteUser(Long id);

    /**
     * 根据页码和每页大小获取用户信息的分页结果
     *
     * @param pageNum  当前页码，用于指定要获取的数据页。
     * @param pageSize 每页包含的用户数量，用于控制分页大小。
     * @return 返回包含用户信息的分页结果对象。
     */
    PageResult<User> getUserPage(Integer pageNum, Integer pageSize);

    /**
     * 分页获取用户信息（逻辑分页）
     *
     * @param pageNum  当前页码，用于指定要获取的数据页。
     * @param pageSize 每页包含的用户数量，用于控制分页大小。
     * @return 返回包含用户信息的分页结果对象。
     */
    PageResult<User> queryUserPage(Integer pageNum, Integer pageSize);

    /**
     * 获取所有用户信息的方法
     *
     * @return 返回包含所有用户信息的列表
     */
    List<User> getAllUser();

    /**
     * 根据用户名获取用户列表
     *
     * @param name 用户名，用于查询用户的参数。
     * @return 返回匹配用户名的用户列表。如果找不到匹配用户，返回空列表。
     */
    List<User> getUserByName(String name);

    /**
     * 根据用户姓名和年龄查询用户列表
     *
     * @param name 用户的姓名，用于精确匹配用户。
     * @param age  用户的年龄，可选参数。如果指定了年龄，则只返回符合该年龄条件的用户。
     * @return 匹配条件的用户列表。如果找不到匹配的用户，返回空列表。
     */
    List<User> getUserByNameAndAge(String name, Integer age);

    /**
     * 批量添加用户信息
     *
     * @param entityList 要保存的用户信息列表。
     * @return 如果所有用户信息都保存成功，则返回true；否则返回false。
     */
    boolean addUserBatch(Collection<AddUserDto> entityList);
}
