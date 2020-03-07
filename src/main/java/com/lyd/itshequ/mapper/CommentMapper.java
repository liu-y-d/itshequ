package com.lyd.itshequ.mapper;

import com.lyd.itshequ.model.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into comment (parent_id,type,commentator,gmt_create,gmt_modified,comment_value) values(#{parentId},#{type},#{commentator},#{gmtCreate},#{gmtModified},#{commentValue})")
    void insert(Comment comment);

    @Select("select * from comment where parent_id = #{parentId}")
    Comment getByParentId(Long parentId);

    @Select("select * from comment where parent_id = #{id} and type = #{type} order by gmt_create desc")
    List<Comment> selectCommentByIdAndType(Long id, Integer type);

    @Select("select * from comment where id = #{id}")
    Comment getByCommentId(Long id);
}
