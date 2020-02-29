package com.lyd.itshequ.service;

import com.lyd.itshequ.bean.PageDTO;
import com.lyd.itshequ.bean.PostDTO;
import com.lyd.itshequ.mapper.PostMapper;
import com.lyd.itshequ.mapper.UserMapper;
import com.lyd.itshequ.model.Post;
import com.lyd.itshequ.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PostServiceImpl
 * @Description TODO
 * @Author Liuyunda
 * @Date 2020/2/23 12:10
 **/
@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostMapper postMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public PageDTO getPostByCreator(Integer id, Integer page, Integer pageSize) {
		Integer pageSum= postMapper.pageSumById(id);
		if (page<1){
			page = 1;
		}

		if (page>pageSum/pageSize+1){
			page = pageSum/pageSize+1;
		}
		Integer offSize = pageSize * (page-1);
		List<Post> postAll = postMapper.getPostByCreator(id,offSize,pageSize);
		List<PostDTO> postDTOS = new ArrayList<>();
		PageDTO pageDTO = new PageDTO();
		for (Post post : postAll){
			User user = userMapper.findById(post.getCreator());
			PostDTO postDTO = new PostDTO();
			BeanUtils.copyProperties(post,postDTO);
			postDTO.setUser(user);
			postDTOS.add(postDTO);
		}
		pageDTO.setData(postDTOS);

		pageDTO.setPageInfo(pageSum,page,pageSize);
		return pageDTO;
	}


	@Override
	public PageDTO getPostAll(Integer page, Integer pageSize) {
		Integer pageSum= postMapper.pageSum();
		if (page<1){
			page = 1;
		}

		if (page>pageSum/pageSize+1){
			page = pageSum/pageSize+1;
		}
		Integer offSize = pageSize * (page-1);
		List<Post> postAll = postMapper.getPostAll(offSize,pageSize);
		List<PostDTO> postDTOS = new ArrayList<>();
		PageDTO pageDTO = new PageDTO();
		for (Post post : postAll){
			User user = userMapper.findById(post.getCreator());
			PostDTO postDTO = new PostDTO();
			BeanUtils.copyProperties(post,postDTO);
			postDTO.setUser(user);
			postDTOS.add(postDTO);
		}
		pageDTO.setData(postDTOS);

		pageDTO.setPageInfo(pageSum,page,pageSize);
		return pageDTO;
	}

	@Override
	public PostDTO getPostById(Integer id) {
		Post post = postMapper.getPostById(id);
		PostDTO postDTO = new PostDTO();
		BeanUtils.copyProperties(post,postDTO);
		User user = userMapper.findById(post.getCreator());
		postDTO.setUser(user);
		return postDTO;
	}

	@Override
	public void createOrUpdate(Post post) {
		Post postById = postMapper.getPostById(post.getId());
		if (postById!=null){
			post.setGmtCreate(System.currentTimeMillis());
			postMapper.updatePost(post);
		}else {
			post.setGmtCreate(System.currentTimeMillis());
			post.setGmtModified(System.currentTimeMillis());
			postMapper.create(post);
		}
	}
}
