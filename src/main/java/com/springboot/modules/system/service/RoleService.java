package com.springboot.modules.system.service;

import java.util.List;
import java.util.Set;

import com.springboot.modules.system.dto.RoleDto;
import com.springboot.modules.system.entity.Role;
import com.springboot.modules.system.query.RoleQuery;
import com.springboot.utils.PageResultSet;

public interface RoleService {

    PageResultSet<RoleDto> findByPage(RoleQuery roleQuery);

    void createRole(Role role);

    void updateRole(Role role);

    void deleteRole(Long roleId);

    Role findOne(Long roleId);

    List<Role> findAll();

    /**
     * 根据角色编号得到角色标识符列表
     * @param roleIds
     * @return
     */
    Set<String> findRoles(Long... roleIds);

    /**
     * 根据角色编号得到权限字符串列表
     * @param roleIds
     * @return
     */
    Set<String> findPermissions(Long[] roleIds);
}
