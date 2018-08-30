package com.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springboot.modules.system.entity.User;

import tk.mybatis.mapper.mapperhelper.EntityHelper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
		EntityHelper.getOrderByClause(User.class);
	}

}
