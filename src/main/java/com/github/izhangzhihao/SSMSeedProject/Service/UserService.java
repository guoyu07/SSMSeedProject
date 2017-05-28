package com.github.izhangzhihao.SSMSeedProject.Service;

import com.github.izhangzhihao.SSMSeedProject.Mapper.UserMapper;
import com.github.izhangzhihao.SSMSeedProject.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public int deleteByPrimaryKey(String username) {
        return userMapper.deleteByPrimaryKey(username);
    }

    public int insert(User record) {
        return userMapper.insert(record);
    }


    public Optional<User> selectByPrimaryKey(String username) {
        return Optional.ofNullable(userMapper.selectByPrimaryKey(username));
    }

    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    public Optional<List<User>> selectAllUser() {
        return Optional.ofNullable(userMapper.selectAllUser());
    }
}
