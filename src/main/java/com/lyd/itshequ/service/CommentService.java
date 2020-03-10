package com.lyd.itshequ.service;

import com.lyd.itshequ.bean.CommentDTO;
import com.lyd.itshequ.enums.CommentTypeEnum;
import com.lyd.itshequ.model.Comment;
import com.lyd.itshequ.model.User;

import java.util.List;

public interface CommentService {

    void insert(Comment comment, User user);

    List<CommentDTO> listByTargetId(Long id, CommentTypeEnum type);
}
