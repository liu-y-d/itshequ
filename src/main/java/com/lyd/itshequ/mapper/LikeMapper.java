package com.lyd.itshequ.mapper;

import com.lyd.itshequ.bean.LikeDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LikeMapper {
    @Insert("insert into likeinfo (postId,userId,likeStatus) values(#{postId},#{userId},#{likeStatus})")
    Integer like(LikeDTO likeDTO);

    @Select("select * from likeinfo where postId=#{postId} and userId = #{userId}")
    LikeDTO getLikeDTO(LikeDTO likeDTO);

    @Update("update likeinfo set likeStatus=#{likeStatus} where postId=#{postId} and userId=#{userId}")
    Integer updateLikeDTO(LikeDTO likeDTO);

}
