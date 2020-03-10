package com.lyd.itshequ.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName PageDTO
 * @Description TODO
 * @Author Liuyunda
 * @Date 2020/2/23 15:23
 **/
@Data
public class PageDTO<T> {
	private List<T> data;
	private Boolean hasAheah;
	private Boolean hasNext;
	private Integer currentPage;
	private List<Integer> pages = new ArrayList<>();
	private Integer pageSum;
	public void setPageInfo(Integer pageSum, Integer page, Integer pageSize) {
		currentPage = page;
		Integer totalPage = 0;
		if (pageSum%pageSize==0){
			totalPage = pageSum/pageSize;
		}else{
			totalPage = pageSum/pageSize + 1;
		}
		pages.add(page);
		for (int i = 1; i <=3 ; i++) {
			if (page - i > 0){
				pages.add(0,page-i);
			}
			if (page + i <=totalPage){
				pages.add(page + i);
			}
		}
		//是否展示上一页
		if (page == 1){
			hasAheah = false;
		}else{
			hasAheah = true;
		}
		//是否展示下一页
		if (page == totalPage){
			hasNext = false;
		}else {
			hasNext = true;
		}

	}
}
