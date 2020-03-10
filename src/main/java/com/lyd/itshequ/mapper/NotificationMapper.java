package com.lyd.itshequ.mapper;

import com.lyd.itshequ.model.Notification;
import com.lyd.itshequ.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface NotificationMapper {
    @Insert("insert into notification(notifier,receiver,outerId,type,gmt_create,status,notifier_name,outer_title) values (#{notifier},#{receiver},#{outerId},#{type},#{gmtCreate},#{status},#{notifierName},#{outerTitle})")
    void insert(Notification notification);
    @Select("select count(1) from notification where receiver = #{userId}")
    Integer pageSumById(Long userId);
    @Select("select * from notification where  receiver = #{userId} order by gmt_create desc limit #{offSize},#{pageSize}")
    List<Notification> getNotifyByUser(Long userId, Integer offSize, Integer pageSize);
    @Select("select count(1) from notification where receiver = #{userId}")
    Long selectUnReadCount(Long userId);
    @Select("select * from notification where id = #{id}")
    Notification getNotifyById(Long id);
    @Update("update notification set status = #{status} where id = #{id}")
    Integer updateStatus(Notification notification);
    @Select("select count(1) from notification where receiver = #{id} and status = #{status}")
    Integer queryNotifyNumber(Long id,Integer status);
}
