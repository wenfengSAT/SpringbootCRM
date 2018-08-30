package com.springboot.modules.system.service;

import tk.mybatis.mapper.weekend.Weekend;

import java.util.List;

import com.springboot.modules.system.dto.TreeDto;
import com.springboot.modules.system.entity.Organization;

public interface OrganizationService {

    void createOrganization(Organization organization);

    void updateOrganization(Organization organization);

    void deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);

    List<Organization> find(Weekend<?> example);

    List<TreeDto> findOrgTree(Long pId);

    List<Organization> findAll();

    List<Organization> findAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);
}
