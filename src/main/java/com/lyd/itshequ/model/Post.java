package com.lyd.itshequ.model;

import lombok.Data;

/**
 * @ClassName Post
 * @Description TODO
 * @Author Liuyunda
 * @Date 2020/2/22 20:33
 **/
@Data
public class Post {
	private Long id;
	private String title;
	private String description;
	private Long gmtCreate;
	private Long gmtModified;
	private Long creator;
	private Integer commentCount;
	private Integer watchCount;
	private Integer likeCount;
	private String tag;
}
