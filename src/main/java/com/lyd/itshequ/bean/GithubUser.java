package com.lyd.itshequ.bean;

import lombok.Data;

/**
 * @ClassName GithubUser
 * @Description TODO
 * @Author Liuyunda
 * @Date 2020/2/21 18:37
 **/
@Data
public class GithubUser {
	private String name;
	private Long id;
	private String bio;
	private String avatarUrl;
}
