package com.lyd.itshequ.service;

import com.lyd.itshequ.bean.PageDTO;

public interface PostService {
	PageDTO getPostById(Integer id,Integer page, Integer pageSize);
	PageDTO getPostAll(Integer page, Integer pageSize);
}
