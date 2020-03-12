package com.lyd.itshequ.mapper;

import com.lyd.itshequ.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
	@Insert("insert into USER (name,account_id,token,gmt_create,gmt_modified,avatar_url) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
	public void insert(User user);
	@Insert("insert into USER (name,gmt_create,gmt_modified,avatar_url,password,token) values(#{name},#{gmtCreate},#{gmtModified},#{avatarUrl},#{password},#{token})")
	public void insertLocalUser(User user);
	@Select("select * from user where token = #{token}")
	User findByToken(@Param("token") String token);
	@Select("select * from user where name = #{name}")
	User queryByName(@Param("name")String name);
	@Select("select * from user where id = #{id}")
	User findById(@Param("id")Long id);
	@Select("select * from user where name = #{name} and password = #{password}")
	User Login(User user);
	@Select("select * from user where account_id = #{accountId}")
	User findByAccountId(@Param("accountId") String accountId);

	@Update("update user set name = #{name} ,token = #{token},gmt_modified = #{gmtModified}, avatar_url = #{avatarUrl} where id = #{id}")
	void update(User dbUser);

	@Select({
			"<script>",
			"select * from user where id in",
				"<foreach item='item' index='index' collection='userIds'",
				"open='(' separator=',' close=')'>",
				"#{item}",
				"</foreach>",
			"</script>"
	})
    List<User> selectByIdList(@Param("userIds") List<Long> userIds);

	@Update("update user set name = #{name},gmt_modified = #{gmtModified}, avatar_url = #{avatarUrl},password=#{password} where id = #{id}")
	int updateUser(User user);
}
