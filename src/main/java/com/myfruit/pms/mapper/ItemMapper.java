package com.myfruit.pms.mapper;

import com.myfruit.pms.dto.ItemDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ItemMapper { //인터페이스의 기본은 public

    void insertItem(ItemDto itemDto); //Pk 생성
    // itemClass itemMapper.insertItem(itemDto) 전달받고
    ItemDto getItemById(int id); //Pk가 생성, int 성공 또는 실패
    // 1개이상 나오는거는 list
    List<ItemDto> getItems();
    void updateItem(ItemDto itemDto); // 내용 변경
    void deleteItem(int id);
}
