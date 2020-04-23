package com.lyd.itshequ.service;

import com.lyd.itshequ.bean.CollectDTO;

import java.util.List;

public interface CollectService {
    List<CollectDTO> getCollectByUserId(Long userId);

    CollectDTO collect(CollectDTO collectDTO);
    CollectDTO top(CollectDTO collectDTO);

    CollectDTO getCollectDTO(CollectDTO collectDTO);
}
