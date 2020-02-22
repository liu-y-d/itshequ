package com.lyd.itshequ.mapper;

import com.lyd.itshequ.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	@Insert("insert into USER (name,acount_id,token,gmt_create,gmt_modified) values(#{name},#{acountId},#{token},#{gmtCreate},#{gmtModified})")
	public void insert(User user);
}
