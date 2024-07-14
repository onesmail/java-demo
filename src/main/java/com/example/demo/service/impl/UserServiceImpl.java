package com.example.demo.service.impl;


import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.AddUserDto;
import com.example.demo.entity.dto.PageResult;
import com.example.demo.entity.dto.UpdateUserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addUser(AddUserDto addUserDto) {
        User user = Convert.convert(User.class, addUserDto);
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean updateUser(UpdateUserDto updateUserDto) {
        User user = userMapper.selectById(updateUserDto.getId());
        User userInfo = Convert.convert((Type) updateUserDto, user);

        return userMapper.updateById(userInfo) > 0;
    }

    @Override
    public boolean deleteUser(Long id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public PageResult<User> getUser(Integer pageNum, Integer pageSize) {
        Page<User> userPage = new Page<>(pageNum, pageSize);
        userMapper.selectPage(userPage, null);

        return new PageResult<>(userPage.getRecords(), userPage.getTotal());
    }
}
