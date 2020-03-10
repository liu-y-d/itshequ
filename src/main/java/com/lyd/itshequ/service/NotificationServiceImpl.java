package com.lyd.itshequ.service;

import com.lyd.itshequ.bean.NotificationDTO;
import com.lyd.itshequ.bean.PageDTO;
import com.lyd.itshequ.bean.PostDTO;
import com.lyd.itshequ.enums.NotificationEnum;
import com.lyd.itshequ.enums.NotificationStatusEnum;
import com.lyd.itshequ.exception.MeErrorCode;
import com.lyd.itshequ.exception.MeExceptions;
import com.lyd.itshequ.mapper.NotificationMapper;
import com.lyd.itshequ.mapper.UserMapper;
import com.lyd.itshequ.model.Notification;
import com.lyd.itshequ.model.Post;
import com.lyd.itshequ.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public PageDTO list(Long userId, Integer page, Integer pageSize) {
        Integer pageSum= notificationMapper.pageSumById(userId);
        if (page<1){
            page = 1;
        }

        if (page>pageSum/pageSize+1){
            page = pageSum/pageSize+1;
        }
        Integer offSize = pageSize * (page-1);
        List<Notification> notifications = notificationMapper.getNotifyByUser(userId,offSize,pageSize);
        ArrayList<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO e = new NotificationDTO();
            BeanUtils.copyProperties(notification,e);
            e.setTypeName(NotificationEnum.nameOfType(notification.getType()));
            notificationDTOS.add(e);
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setData(notificationDTOS);
        pageDTO.setPageInfo(pageSum,page,pageSize);
        return pageDTO;
    }

    @Override
    public Long unReadCount(Long userId) {
        return notificationMapper.selectUnReadCount(userId);
    }

    @Override
    public NotificationDTO read(Long id, User user) {
        Notification notification = notificationMapper.getNotifyById(id);
        if (notification == null){
            throw new MeExceptions(MeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (notification.getReceiver() != user.getId()){
            throw new MeExceptions(MeErrorCode.READ_ERROR);
        }
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        Integer integer = notificationMapper.updateStatus(notification);
        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        notificationDTO.setTypeName(NotificationEnum.nameOfType(notification.getType()));

        return notificationDTO;
    }

    @Override
    public Integer queryNotifyNumber(Long id,Integer status) {
        Integer integer = notificationMapper.queryNotifyNumber(id, status);
        return integer;
    }
}
