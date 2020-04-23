package com.lyd.itshequ.service;

import com.lyd.itshequ.bean.CollectDTO;
import com.lyd.itshequ.bean.CommentDTO;
import com.lyd.itshequ.exception.MeErrorCode;
import com.lyd.itshequ.exception.MeExceptions;
import com.lyd.itshequ.mapper.CollectMapper;
import com.lyd.itshequ.mapper.PostMapper;
import com.lyd.itshequ.model.Post;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private PostMapper postMapper;
    @Override
    public List<CollectDTO> getCollectByUserId(Long userId) {
        List<CollectDTO> collectDTOByUserId = collectMapper.getCollectDTOByUserId(userId);
        List<CollectDTO> dtos = new ArrayList<>();
        for (CollectDTO collectDTO : collectDTOByUserId) {
            Post postById = postMapper.getPostById(collectDTO.getPostId());
            CollectDTO collectDTO1 = new CollectDTO();
            collectDTO1.setUserId(collectDTO.getUserId());
            collectDTO1.setPostId(collectDTO.getPostId());
            collectDTO1.setPost(postById);
            collectDTO1.setTop(collectDTO.getTop());
            dtos.add(collectDTO1);
        }
        return dtos;
    }

    @Override
    public CollectDTO collect(CollectDTO collectDTO) {
        CollectDTO dto = getCollectDTO(collectDTO);
        if (dto == null){
            int collect = collectMapper.collect(collectDTO);
            if (collect ==1 ){
                return collectDTO;
            }else {
                throw new MeExceptions(MeErrorCode.SYS_ERROR);
            }
        }else {
            int i = collectMapper.delCollect(collectDTO);
            return null;
        }
    }

    @Override
    public CollectDTO top(CollectDTO collectDTO) {
        CollectDTO dto = getCollectDTO(collectDTO);
        if (dto != null ){
            //如果能找到收藏信息，那么判断置顶字段的值如果原值为0则设置为1反之设置为0
            if(dto.getTop() == 0){
                collectDTO.setTop(1);
            }else {
                collectDTO.setTop(0);
            }
            int collect = collectMapper.top(collectDTO);
            if (collect ==1 ){
                return collectDTO;
            }else {
                throw new MeExceptions(MeErrorCode.SYS_ERROR);
            }
        }
        return null;
    }

    @Override
    public CollectDTO getCollectDTO(CollectDTO collectDTO) {
        CollectDTO collectDTO1 = collectMapper.getCollectDTO(collectDTO);
        return collectDTO1;
    }
}
