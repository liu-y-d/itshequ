package com.lyd.itshequ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ItshequApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItshequApplication.class, args);
	}

}
