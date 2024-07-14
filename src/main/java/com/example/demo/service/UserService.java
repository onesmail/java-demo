package com.example.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.User;
import com.example.demo.entity.dto.AddUserDto;
import com.example.demo.entity.dto.PageResult;
import com.example.demo.entity.dto.UpdateUserDto;

public interface UserService {

    boolean addUser(AddUserDto addUserDto);

    boolean updateUser(UpdateUserDto updateUserDto);

    boolean deleteUser(Long id);

    PageResult getUser(Integer pageNum, Integer pageSize);

}
