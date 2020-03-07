package com.lyd.itshequ.service;

import com.lyd.itshequ.bean.PageDTO;
import com.lyd.itshequ.bean.PostDTO;
import com.lyd.itshequ.model.Post;

public interface PostService {
	PageDTO getPostByCreator(Long id,Integer page, Integer pageSize);
	PageDTO getPostAll(Integer page, Integer pageSize);
	PostDTO getPostById(Long id);

	void createOrUpdate(Post post);

    PostDTO incView(Long id);
}
