package com.lyd.itshequ.cache;

import com.lyd.itshequ.bean.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCache {
    public static List<TagDTO> get(){
        ArrayList<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO cuisine = new TagDTO();
        cuisine.setTagClass("菜系");
        cuisine.setTags(Arrays.asList("川菜","粤菜","鲁菜","苏菜","闽菜","浙菜","湘菜","徽菜"));
        tagDTOS.add(cuisine);
        TagDTO taste = new TagDTO();
        taste.setTagClass("口味");
        taste.setTags(Arrays.asList("微辣","麻辣","变态辣","香辣","酸辣","酸甜"));
        tagDTOS.add(taste);
        TagDTO area = new TagDTO();
        area.setTagClass("地区");
        area.setTags(Arrays.asList("南方","北方"));
        tagDTOS.add(area);
        TagDTO type = new TagDTO();
        type.setTagClass("类型");
        type.setTags(Arrays.asList("主食","副食","饭后甜点","营养餐"));
        tagDTOS.add(type);
        return tagDTOS;
    }
    public static String isValid(String tags){
        String[] split = StringUtils.split(tags, ',');
        List<TagDTO> tagDTOS = get();
        List<String> stringList = tagDTOS.stream().flatMap(tagDTO -> tagDTO.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !stringList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
