package com.lyd.itshequ.model;

/**
 * @ClassName Post
 * @Description TODO
 * @Author Liuyunda
 * @Date 2020/2/22 20:33
 **/
public class Post {
	private Integer id;
	private String title;
	private String description;
	private Long gmtCreate;
	private Long gmtModified;
	private Integer creator;
	private Integer commentCount;
	private Integer watchCount;
	private Integer likeCount;
	private String tag;

	@Override
	public String toString() {
		return "Post{" +
				"id=" + id +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				", gmtCreate=" + gmtCreate +
				", gmtModified=" + gmtModified +
				", creator=" + creator +
				", commentCount=" + commentCount +
				", watchCount=" + watchCount +
				", likeCount=" + likeCount +
				", tag='" + tag + '\'' +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getCreator() {
		return creator;
	}

	public void setCreator(Integer creator) {
		this.creator = creator;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getWatchCount() {
		return watchCount;
	}

	public void setWatchCount(Integer watchCount) {
		this.watchCount = watchCount;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
