package com.springboot.modules.system.service;

import java.util.List;
import java.util.Set;

import com.springboot.core.exception.CrmException;
import com.springboot.modules.system.dto.UserDto;
import com.springboot.modules.system.entity.User;
import com.springboot.modules.system.query.UserQuery;
import com.springboot.utils.PageResultSet;

public interface UserService {

    PageResultSet<UserDto> findByPage(UserQuery userQuery);

    /**
     * 创建用户
     * @param user
     */
    void createUser(User user) throws CrmException;

    void updateUser(User user);

    void deleteUser(Long userId);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    void changePassword(Long userId, String newPassword);


    User findOne(Long userId);

    List<User> findAll();

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    public User findByUsername(String username);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    Set<String> findPermissions(String username);

}