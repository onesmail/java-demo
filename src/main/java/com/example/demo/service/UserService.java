package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.AddUserDto;
import com.example.demo.entity.dto.PageResult;
import com.example.demo.entity.dto.UpdateUserDto;

import java.util.List;

public interface UserService {

    /**
     * 添加用户方法
     *
     * @param addUserDto 包含用户添加信息的Dto（数据传输对象），用于传递用户相关数据到业务逻辑层。
     * @return Long 表示用户添加操作的结果，返回插入成功的主键id。
     */
    Long addUser(AddUserDto addUserDto);

    /**
     * 更新用户信息的方法。
     *
     * @param updateUserDto 包含待更新用户信息的数据传输对象。
     * @return 如果用户信息更新成功，则返回true；否则返回false。
     */
    boolean updateUser(UpdateUserDto updateUserDto);

    /**
     * 删除用户。
     *
     * @param id 用户的ID，作为删除操作的唯一标识。
     * @return 如果成功删除用户，则返回true；否则返回false。
     */
    boolean deleteUser(Long id);

    /**
     * 根据页码和每页大小获取用户信息的分页结果。
     *
     * @param pageNum  当前页码，用于指定要获取的数据页。
     * @param pageSize 每页包含的用户数量，用于控制分页大小。
     * @return 返回包含用户信息的分页结果对象。
     */
    PageResult getUser(Integer pageNum, Integer pageSize);

    /**
     * 获取所有用户信息的方法
     *
     * @return 返回包含所有用户信息的列表
     */
    List<User> getAllUser();

    /**
     * 根据用户名获取用户列表。
     *
     * @param name 用户名，用于查询用户的参数。
     * @return 返回匹配用户名的用户列表。如果找不到匹配用户，返回空列表。
     */
    List<User> getUserByName(String name);
}
