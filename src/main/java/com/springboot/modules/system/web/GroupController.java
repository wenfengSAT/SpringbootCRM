package com.springboot.modules.system.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.springboot.core.annotation.SystemLog;
import com.springboot.modules.system.entity.Group;
import com.springboot.modules.system.enums.GroupType;
import com.springboot.modules.system.query.GroupQuery;
import com.springboot.modules.system.service.GroupService;
import com.springboot.utils.PageResultSet;
import com.springboot.utils.Result;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * 
 * @Description： 功能描述
 * @author [ Wenfeng.Huang ] on [2018年8月24日下午5:30:21]
 * @Modified By： [修改人] on [修改日期] for [修改说明]
 *
 */
@Controller
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public String page(Model model) {
        setCommonData(model);
        return "system/group";
    }

    @ResponseBody
    @RequestMapping("/list")
    public PageResultSet<Group> list(GroupQuery groupQuery) {
        return groupService.findByPage(groupQuery);
    }

    @ResponseBody
    @PostMapping("/create")
    @SystemLog("用户管理创建用户组")
    public Result<?> create(@Valid Group group) {
        groupService.createGroup(group);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/update")
    @SystemLog("用户管理更新用户组")
    public Result<?> update(@Valid Group group) {
        groupService.updateGroup(group);
        return Result.success();
    }

    @ResponseBody
    @PostMapping("/delete")
    @SystemLog("用户管理删除用户组")
    public Result<?> delete(@RequestParam("id") Long[] ids) {
        Arrays.asList(ids).forEach(id -> groupService.deleteGroup(id));
        return Result.success();
    }

    private void setCommonData(Model model) {
        model.addAttribute("groupTypeList", GroupType.values());
    }

}
