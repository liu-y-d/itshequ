package com.lyd.itshequ.bean;

import com.lyd.itshequ.model.User;
import lombok.Data;

/**
 * @ClassName PostDTO
 * @Description TODO
 * @Author Liuyunda
 * @Date 2020/2/23 12:06
 **/
@Data
public class PostDTO {
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
	private User user;
}
