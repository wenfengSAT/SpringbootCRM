package com.springboot.modules.system.query;

import com.springboot.modules.system.entity.Role;
import com.springboot.utils.BaseQuery;

public class RoleQuery extends BaseQuery<Role> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 角色标识 程序中判断使用,如"admin"
     */
    private String role;
    /**
     * 角色描述,UI界面显示使用
     */
    private String description;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}
