package com.lyd.itshequ.service;

import com.lyd.itshequ.bean.CommentDTO;
import com.lyd.itshequ.enums.CommentTypeEnum;
import com.lyd.itshequ.model.Comment;

import java.util.List;

public interface CommentService {

    void insert(Comment comment);

    List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type);
}
