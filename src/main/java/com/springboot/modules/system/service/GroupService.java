package com.springboot.modules.system.service;

import java.util.List;

import com.springboot.modules.system.entity.Group;
import com.springboot.modules.system.query.GroupQuery;
import com.springboot.utils.PageResultSet;

public interface GroupService {

    /**
     * 分页查询用户组
     * @param groupQuery
     * @return
     */
    PageResultSet<Group> findByPage(GroupQuery groupQuery);

    /**
     * 查询所有
     * @return
     */
    List<Group> findAll();

    /**
     * 查询单个
     * @param groupId
     * @return
     */
    Group findOne(Long groupId);

    /**
     * 创建用户组
     * @param group
     */
    void createGroup(Group group);

    /**
     * 更新用户组
     * @param group
     */
    void updateGroup(Group group);

    /**
     * 删除用户组
     * @param groupId
     */
    void deleteGroup(Long groupId);

}
