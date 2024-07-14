package com.example.demo.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.Collection;
import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名获取用户列表。
     *
     * @param username 用户名，用于查询用户的参数。
     * @return 返回匹配用户名的用户列表。如果找不到匹配用户，返回空列表。
     */
    List<User> getUserByName(@Param("username") String username);

    /**
     * 根据用户名和年龄查询用户列表。
     *
     * @param queryWrapper 包含查询条件的Wrapper对象。使用@Param注解指明参数名称，以便MyBatis-plus能够正确解析查询条件。
     * @return 返回符合条件的用户列表。列表中的每个元素都是User类型的对象，代表一个用户。
     */
    List<User> getUserByNameAndAge(@Param(Constants.WRAPPER) Wrapper<User> queryWrapper);

    /**
     * 批量添加用户信息。
     *
     * @param entityList 要保存的用户信息列表。
     * @return 如果所有用户信息都保存成功，则返回true；否则返回false。
     */
    boolean saveUserBatch(Collection<User> entityList);

    /**
     * 根据页码和每页大小获取用户信息的分页结果（逻辑分页）。
     *
     * @param rowbounds 数据分页对象。
     * @return 返回包含用户信息的分页结果对象。
     */
    List<User> queryUserPage(RowBounds rowbounds);
}
