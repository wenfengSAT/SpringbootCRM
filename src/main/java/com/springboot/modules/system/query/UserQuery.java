package com.springboot.modules.system.query;

import com.springboot.modules.system.entity.User;
import com.springboot.utils.BaseQuery;

public class UserQuery extends BaseQuery<User> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 用户名
     */
    private String username;

    private Boolean locked;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
