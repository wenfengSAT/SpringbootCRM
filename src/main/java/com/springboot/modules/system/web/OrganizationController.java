package com.springboot.modules.system.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.springboot.core.annotation.SystemLog;
import com.springboot.modules.system.dto.TreeDto;
import com.springboot.modules.system.entity.Organization;
import com.springboot.modules.system.service.OrganizationService;
import com.springboot.utils.BaseController;
import com.springboot.utils.Result;

import tk.mybatis.mapper.weekend.Weekend;

import javax.validation.Valid;
import java.util.List;

/**
 * 
 * @Description： 功能描述
 * @author [ Wenfeng.Huang ] on [2018年8月24日下午5:31:08]
 * @Modified By： [修改人] on [修改日期] for [修改说明]
 *
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController{

    @Autowired
    private OrganizationService organizationService;

    @GetMapping
    @RequiresPermissions("organization:view")
    public String page() {
        Weekend<Organization> example = Weekend.of(Organization.class);
        example.setOrderByClause("priority");
        return "system/organization";
    }

    @ResponseBody
    @RequestMapping("/tree")
    public List<TreeDto> findOrgTree(Long pId) {
        return organizationService.findOrgTree(pId);
    }

    @ResponseBody
    @RequiresPermissions("organization:view")
    @RequestMapping(value = "{id}/load", method = RequestMethod.POST)
    public Result<?> load(@PathVariable Long id) {
        Organization organization = organizationService.findOne(id);
        return Result.success(organization);
    }

    @ResponseBody
    @RequiresPermissions("organization:create")
    @SystemLog("组织管理创建组织")
    @PostMapping("/create")
    public Result<?> create(@Valid Organization organization) {
        organizationService.createOrganization(organization);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("organization:update")
    @SystemLog("组织管理更新组织")
    @PostMapping("/update")
    public Result<?> update(@Valid Organization organization) {
        organizationService.updateOrganization(organization);
        return Result.success();
    }

    @ResponseBody
    @RequiresPermissions("organization:delete")
    @SystemLog("组织管理删除组织")
    @PostMapping("/delete")
    public Result<?> delete(Long id) {
        organizationService.deleteOrganization(id);
        return Result.success();
    }

}
