package com.springboot.modules.system.service;

import com.springboot.modules.system.entity.Log;
import com.springboot.modules.system.query.LogQuery;
import com.springboot.utils.PageResultSet;

public interface LogService {

    /**
     * 创建日志
     * @param log
     * @return
     */
    void create(Log log);

    /**
     * 分页查询日志
     * @param log
     * @return
     */
    PageResultSet<Log> findByPage(LogQuery log);

}
