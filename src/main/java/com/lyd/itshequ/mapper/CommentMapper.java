package com.lyd.itshequ.mapper;

import com.lyd.itshequ.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment (parent_id,type,commentator,gmt_create,gmt_modified,comment_value) values(#{parentId},#{type},#{commentator},#{gmtCreate},#{gmtModified},#{commentValue})")
    void insert(Comment comment);
}
