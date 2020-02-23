package com.lyd.itshequ.bean;

import lombok.Data;

/**
 * @ClassName AccessTokenDTO
 * @Description githu登录配置对象
 * @Author Liuyunda
 * @Date 2020/2/21 18:36
 **/
@Data
public class AccessTokenDTO {
	private String client_id;
	private String client_secret;
	private String code;
	private String redirect_uri;
	private String state;
}
