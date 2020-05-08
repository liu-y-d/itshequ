package com.lyd.itshequ.service;

import com.lyd.itshequ.bean.PageDTO;
import com.lyd.itshequ.bean.PostDTO;
import com.lyd.itshequ.model.Post;

import java.util.List;

public interface PostService {
	PageDTO getPostByCreator(Long id,Integer page, Integer pageSize);
	PageDTO getPostAll(String search, Integer page, Integer pageSize);
	PostDTO getPostById(Long id);

	void createOrUpdate(Post post);

    PostDTO incView(Long id);

	List<PostDTO> selectByRelated(PostDTO postById);
	List<Post> UPLeaderBoard();
}
