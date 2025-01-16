package com.myfruit.pms.mapper;

import com.myfruit.pms.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper {

    void insertItem(ItemDto itemDto);
    // itemClass itemMapper.insertItem(itemDto) 전달받고
}
