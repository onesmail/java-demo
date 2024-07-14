package com.example.demo.service.impl;


import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.AddUserDto;
import com.example.demo.entity.dto.PageResult;
import com.example.demo.entity.dto.UpdateUserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 添加用户方法
     *
     * @param addUserDto 包含用户添加信息的Dto（数据传输对象），用于传递用户相关数据到业务逻辑层。
     * @return Integer 表示用户添加操作的结果，返回插入成功的主键id。
     */
    @Override
    public Long addUser(AddUserDto addUserDto) {
        User user = Convert.convert(User.class, addUserDto);

        userMapper.insert(user);
        return user.getId();
    }

    /**
     * 更新用户信息的方法。
     *
     * @param updateUserDto 包含待更新用户信息的数据传输对象。
     * @return 如果用户信息更新成功，则返回true；否则返回false。
     */
    @Override
    public boolean updateUser(UpdateUserDto updateUserDto) {
        User user = userMapper.selectById(updateUserDto.getId());
        if (user == null) {
            // 处理user为null的情况，例如返回错误信息或抛出异常
            throw new IllegalArgumentException("用户不存在");
        }
        BeanUtils.copyProperties(updateUserDto, user);

        return userMapper.updateById(user) > 0;
    }

    /**
     * 删除用户。
     *
     * @param id 用户的ID，作为删除操作的唯一标识。
     * @return 如果成功删除用户，则返回true；否则返回false。
     */
    @Override
    public boolean deleteUser(Long id) {
        return userMapper.deleteById(id) > 0;
    }

    /**
     * 根据页码和每页大小获取用户信息的分页结果。
     *
     * @param pageNum  当前页码，用于指定要获取的数据页。
     * @param pageSize 每页包含的用户数量，用于控制分页大小。
     * @return 返回包含用户信息的分页结果对象。
     */
    @Override
    public PageResult<User> getUserPage(Integer pageNum, Integer pageSize) {

        Page<User> userPage = new Page<>(pageNum, pageSize);
        userMapper.selectPage(userPage, null);

        return new PageResult<>(userPage.getRecords(), userPage.getTotal());
    }

    /**
     * 分页获取用户信息（逻辑分页）。
     *
     * @param pageNum  当前页码，用于指定要获取的数据页。
     * @param pageSize 每页包含的用户数量，用于控制分页大小。
     * @return 返回包含用户信息的分页结果对象。
     */
    @Override
    public PageResult<User> queryUserPage(Integer pageNum, Integer pageSize) {
        List<User> list = userMapper.queryUserPage(new RowBounds((pageNum - 1) * pageSize, pageSize));
        return new PageResult(list, userMapper.selectCount(null));
    }

    /**
     * 获取所有用户信息的方法
     *
     * @return 返回包含所有用户信息的列表
     */
    @Override
    public List<User> getAllUser() {
        return userMapper.selectList(null);
    }

    /**
     * 根据用户名获取用户列表。
     *
     * @param username 用户名，用于查询用户的参数。
     * @return 返回匹配用户名的用户列表。如果找不到匹配用户，返回空列表。
     */
    public List<User> getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    /**
     * 根据用户姓名和年龄查询用户列表。
     *
     * @param username 用户的姓名，用于精确匹配用户。
     * @param age      用户的年龄，可选参数。如果指定了年龄，则只返回符合该年龄条件的用户。
     * @return 匹配条件的用户列表。如果找不到匹配的用户，返回空列表。
     */
    @Override
    public List<User> getUserByNameAndAge(String username, Integer age) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        if (!username.isEmpty()) {
            queryWrapper.like("username", username);
        }

        if (age != null) {
            queryWrapper.eq("age", age);
        }

        return userMapper.getUserByNameAndAge(queryWrapper);
    }

    /**
     * 批量添加用户信息。
     *
     * @param entityList 要保存的用户信息列表。
     * @return 如果所有用户信息都保存成功，则返回true；否则返回false。
     */
    @Override
    @Transactional
    public boolean addUserBatch(Collection<AddUserDto> entityList) {
        List<User> list = Convert.convert(new TypeReference<List<User>>() {
        }, entityList);

        if (entityList.isEmpty()) {
            return false;
        }

        return userMapper.saveUserBatch(list);
    }
}
