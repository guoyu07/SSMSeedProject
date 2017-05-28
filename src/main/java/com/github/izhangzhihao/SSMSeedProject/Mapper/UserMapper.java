package com.github.izhangzhihao.SSMSeedProject.Mapper;

import com.github.izhangzhihao.SSMSeedProject.Model.User;

import java.util.List;

public interface UserMapper {

    int deleteByPrimaryKey(String username);

    int insert(User record);

    User selectByPrimaryKey(String username);

    int updateByPrimaryKey(User record);

    List<User> selectAllUser();
}