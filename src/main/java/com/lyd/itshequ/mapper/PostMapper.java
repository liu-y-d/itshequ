package com.lyd.itshequ.mapper;

import com.lyd.itshequ.model.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Liuyunda
 */
@Mapper
public interface PostMapper {
	@Insert("insert into Post (title,description,gmt_create,gmt_modified,creator,tag) " +
			"values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
	void create(Post post);

	@Select("select * from post limit #{offSize},#{pageSize}")
	List<Post> getPostAll(@Param("offSize") Integer offSize,@Param("pageSize") Integer pageSize);
	@Select("select * from post where creator = #{id} limit #{offSize},#{pageSize}")
	List<Post> getPostByCreator(@Param("id")Integer id,@Param("offSize") Integer offSize,@Param("pageSize") Integer pageSize);
	@Select("select count(1) from post")
	Integer pageSum();
	@Select("select count(1) from post where creator = #{id}")
	Integer pageSumById(@Param("id")Integer id);
	@Select("select * from post where id = #{id}")
	Post getPostById(@Param("id") Integer id);
}
