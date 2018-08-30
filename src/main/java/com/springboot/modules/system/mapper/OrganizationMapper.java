package com.springboot.modules.system.mapper;

import com.springboot.modules.system.entity.Organization;
import com.springboot.utils.MyMapper;

public interface OrganizationMapper extends MyMapper<Organization> {

    int updateSalefParentIds(String makeSelfAsParentIds);

}