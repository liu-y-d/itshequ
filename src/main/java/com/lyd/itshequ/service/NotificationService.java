package com.lyd.itshequ.service;

import com.lyd.itshequ.bean.NotificationDTO;
import com.lyd.itshequ.bean.PageDTO;
import com.lyd.itshequ.model.User;

public interface NotificationService {
    PageDTO list(Long id, Integer page, Integer pageSize);

    Long unReadCount(Long id);

    NotificationDTO read(Long id, User user);

    Integer queryNotifyNumber(Long id,Integer status);
}
