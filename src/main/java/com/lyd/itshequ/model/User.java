package com.lyd.itshequ.model;

/**
 * @ClassName User
 * @Description TODO
 * @Author Liuyunda
 * @Date 2020/2/22 13:57
 **/
public class User {
	private Integer id;
	private String name;
	private String acountId;
	private String token;
	private Long gmtCreate;
	private Long gmtModified;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcountId() {
		return acountId;
	}

	public void setAcountId(String acountId) {
		this.acountId = acountId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Long gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Long getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Long gmtModified) {
		this.gmtModified = gmtModified;
	}
}
