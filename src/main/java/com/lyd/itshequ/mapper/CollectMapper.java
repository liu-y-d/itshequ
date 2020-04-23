package com.lyd.itshequ.mapper;

import com.lyd.itshequ.bean.CollectDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CollectMapper {
    @Select("select * from collectInfo where userId = #{userId} order by top desc")
    List<CollectDTO> getCollectDTOByUserId(Long id);

    @Select("select * from collectInfo where userId = #{userId} and postId = #{postId} ")
    CollectDTO getCollectDTO(CollectDTO collectDTO);

    @Insert("insert into collectInfo (userId,postId) values(#{userId},#{postId})")
    int collect(CollectDTO collectDTO);

    @Delete("delete from collectInfo where userId = #{userId} and postId=#{postId}")
    int delCollect(CollectDTO collectDTO);

    @Update("update collectInfo set top = #{top} where userId = #{userId} and postId=#{postId}")
    int top(CollectDTO collectDTO);
}
