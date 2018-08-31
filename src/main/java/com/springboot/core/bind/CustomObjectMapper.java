package com.springboot.core.bind;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;

import javax.annotation.Resource;

/**
 * 
 * @Description： 自定义JSON转换器
 * @author [ Wenfeng.Huang ] on [2018年8月24日下午5:27:50]
 * @Modified By： [修改人] on [修改日期] for [修改说明]
 *
 */
@Resource
public class CustomObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	public CustomObjectMapper() {
        super();
        SimpleModule simpleModule = new SimpleModule();
        // 所有Long类型转换String类型
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        // 禁用空对象转换json校验
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.registerModule(simpleModule);
        // 设置null转换""
//        this.getSerializerProvider().setNullValueSerializer(new NullSerializer());
        // 设置日期转换yyyy-MM-dd HH:mm:ss
        // setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // 不显示为null的字段
        // this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

}
