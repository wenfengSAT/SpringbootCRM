package com.springboot.modules.system.mapper;

import com.springboot.modules.system.po.Organization;
import com.springboot.utils.MyMapper;

public interface OrganizationMapper extends MyMapper<Organization> {

    int updateSalefParentIds(String makeSelfAsParentIds);

}