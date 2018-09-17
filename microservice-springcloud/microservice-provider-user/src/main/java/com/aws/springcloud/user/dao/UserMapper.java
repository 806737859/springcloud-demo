package com.aws.springcloud.user.dao;

import com.aws.springcloud.user.entity.User;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();

    List<User> selectBybalance(Double balance);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}