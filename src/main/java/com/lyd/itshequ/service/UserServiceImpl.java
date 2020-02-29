package com.lyd.itshequ.service;

import com.lyd.itshequ.mapper.UserMapper;
import com.lyd.itshequ.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Liuyunda
 * @Date 2020/2/24 10:15
 **/
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public void createOrUpdate(User user) {
		User dbUser = userMapper.findByAccountId(user.getAccountId());
		if (dbUser==null){
			// 插入
			user.setGmtCreate(System.currentTimeMillis());
			user.setGmtModified(user.getGmtCreate());
			userMapper.insert(user);
		}else {
			// 更新
			dbUser.setGmtModified(System.currentTimeMillis());
			dbUser.setAvatarUrl(user.getAvatarUrl());
			dbUser.setToken(user.getToken());
			dbUser.setName(user.getName());
			userMapper.update(dbUser);
		}
	}
}
