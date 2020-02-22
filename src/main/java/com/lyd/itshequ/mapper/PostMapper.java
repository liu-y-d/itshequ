package com.lyd.itshequ.mapper;

import com.lyd.itshequ.model.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Liuyunda
 */
@Mapper
public interface PostMapper {
	@Insert("insert into Post (title,description,gmt_create,gmt_modified,creator,tag) " +
			"values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
	void create(Post post);
}
