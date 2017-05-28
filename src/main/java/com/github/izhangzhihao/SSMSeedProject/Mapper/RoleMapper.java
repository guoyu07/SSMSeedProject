package com.github.izhangzhihao.SSMSeedProject.Mapper;

import com.github.izhangzhihao.SSMSeedProject.Model.Role;

import java.util.List;

public interface RoleMapper {
    Role selectByPrimaryKey(String roleName);

    void deleteByPrimaryKey(String roleName);

    void insert(Role role);

    List<?> selectAllRole();
}
