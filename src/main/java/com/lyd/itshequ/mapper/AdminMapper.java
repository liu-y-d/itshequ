package com.lyd.itshequ.mapper;

import com.lyd.itshequ.model.Admin;
import com.lyd.itshequ.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper {
    @Select("select * from admin where adminName = #{adminName} and adminPwd=#{adminPwd}")
    Admin adminLogin(Admin admin);
    @Select("select * from user")
    List<User> getAllUser();
}
