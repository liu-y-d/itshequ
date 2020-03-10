package com.lyd.itshequ.bean;

import lombok.Data;

import java.util.List;

@Data
public class TagDTO {
    private String tagClass;
    private List<String> tags;
}
