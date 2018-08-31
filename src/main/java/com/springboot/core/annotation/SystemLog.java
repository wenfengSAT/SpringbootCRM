package com.springboot.core.annotation;

import java.lang.annotation.*;

/**
 * 
 * @Description：日志注解 拦截controller请求
 * @author [ Wenfeng.Huang ] on [2018年8月24日下午5:27:11]
 * @Modified By： [修改人] on [修改日期] for [修改说明]
 *
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

//  String module()  default "";  //例：模块名称 系统管理-用户管理－列表页面 暂不需要

    /**
     * 日志描述<br/>
     * 例如：新增用户
     */
    String value() default "";

    /**
     * 日志描述<br/>
     * 例如：新增用户
     */
    String desc()  default "";

}
