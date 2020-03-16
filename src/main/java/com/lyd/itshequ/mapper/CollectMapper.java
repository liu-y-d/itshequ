package com.lyd.itshequ.mapper;

import com.lyd.itshequ.bean.CollectDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectMapper {
    @Select("select * from collectInfo where userId = #{userId}")
    List<CollectDTO> getCollectDTOByUserId(Long id);

    @Select("select * from collectInfo where userId = #{userId} and postId = #{postId}")
    CollectDTO getCollectDTO(CollectDTO collectDTO);

    @Insert("insert into collectInfo (userId,postId) values(#{userId},#{postId})")
    int collect(CollectDTO collectDTO);

    @Delete("delete from collectInfo where userId = #{userId} and postId=#{postId}")
    int delCollect(CollectDTO collectDTO);
}
