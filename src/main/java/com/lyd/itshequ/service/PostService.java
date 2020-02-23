package com.lyd.itshequ.service;

import com.lyd.itshequ.bean.PageDTO;
import com.lyd.itshequ.bean.PostDTO;

public interface PostService {
	PageDTO getPostByCreator(Integer id,Integer page, Integer pageSize);
	PageDTO getPostAll(Integer page, Integer pageSize);
	PostDTO getPostById(Integer id);
}
