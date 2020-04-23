package com.lyd.itshequ;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class ItshequApplicationTests {

	@Resource
	private RedisTemplate redisTemplate;
	@Test
	void contextLoads() {
		redisTemplate.opsForValue().set("name","li");
		String name = (String) redisTemplate.opsForValue().get("name");
		System.out.println(name);
	}

}
