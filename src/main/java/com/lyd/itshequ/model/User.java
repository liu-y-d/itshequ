package com.lyd.itshequ.model;

import lombok.Data;

/**
 * @ClassName User
 * @Description TODO
 * @Author Liuyunda
 * @Date 2020/2/22 13:57
 **/
@Data
public class User {
	private Long id;
	private String name;
	private String accountId;
	private String token;
	private Long gmtCreate;
	private Long gmtModified;
	private String avatarUrl;
	private String password;
}
