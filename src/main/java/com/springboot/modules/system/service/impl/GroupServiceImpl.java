package com.springboot.modules.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.springboot.modules.system.entity.Group;
import com.springboot.modules.system.mapper.GroupMapper;
import com.springboot.modules.system.query.GroupQuery;
import com.springboot.modules.system.service.GroupService;
import com.springboot.utils.PageResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public PageResultSet<Group> findByPage(GroupQuery groupQuery) {

        if (!StringUtils.isEmpty(groupQuery.getOrderBy())) {
            PageHelper.orderBy(groupQuery.getOrderBy());
        }
        Weekend<Group> weekend = Weekend.of(Group.class);
        WeekendCriteria<Group, Object> criteria = weekend.weekendCriteria();
        if (!StringUtils.isEmpty(groupQuery.getName())) {
            criteria.andLike(Group::getName, groupQuery.getName());
        }

        PageResultSet<Group> resultSet = new PageResultSet<>();
        Page<Group> page = PageHelper.offsetPage(groupQuery.getOffset(), groupQuery.getLimit()).doSelectPage(() -> groupMapper.selectByExample(weekend));
        resultSet.setRows(page);
        resultSet.setTotal(page.getTotal());
        return resultSet;
    }

    @Override
    public List<Group> findAll() {
        return groupMapper.selectAll();
    }

    @Override
    public Group findOne(Long groupId) {
        return groupMapper.selectByPrimaryKey(groupId);
    }

    @Override
    public void createGroup(Group group) {
        groupMapper.insertSelective(group);
    }

    @Override
    public void updateGroup(Group group) {
        groupMapper.updateByPrimaryKeySelective(group);
    }

    @Override
    public void deleteGroup(Long groupId) {
        groupMapper.deleteByPrimaryKey(groupId);
    }
}
