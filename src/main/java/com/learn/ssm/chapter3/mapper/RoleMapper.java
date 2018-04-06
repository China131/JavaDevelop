package com.learn.ssm.chapter3.mapper;

import com.learn.ssm.chapter3.pojo.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by jianhao on 2018/4/3.
 */
public interface RoleMapper {

    public Role getRole(Long id);
    public int insertRole(Role role);
    public int deleteRole(Long id);
    public int updateRole(Role role);
    public List<Role> findRoles(String roleName);
}
