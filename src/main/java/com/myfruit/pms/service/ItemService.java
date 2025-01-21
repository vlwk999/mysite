package com.myfruit.pms.service;

import com.myfruit.pms.dto.ItemDto;
import com.myfruit.pms.dto.PageDto;
import com.myfruit.pms.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemMapper itemMapper;

    public void createItem(ItemDto itemDto){
        itemMapper.insertItem(itemDto);
    }

    public ItemDto getItem(int id){
        // NullException
//        Optional<ItemDto> itemDto = itemMapper.getItemById(id);
//        itemDto.orElseThrow();

        return itemMapper.getItemById(id).orElseThrow(
                ()-> new IllegalStateException("파일을 찾을 수 없습니다.")
        );
    }

    public PageDto getItems( int page, int limit) {
        int offset = (page -1) * limit;
        // 갯수가 size 인 item 목록
        List <ItemDto> items = itemMapper.getItems(limit, offset);
        // 총 갯수
        int totalElements = itemMapper.countTotal();
        // 총 페이지
        // 13/5 =2.xxx, 3 Math.ceil(2.xxx) 3.03 => 3 (int) 정수 형 변환
        int totalPages = (int) Math.ceil((double)totalElements / limit);

        PageDto pageDto = new PageDto(page, limit, totalPages, totalElements, items);

        // page, limit, items, totalElements 와 totalPages 를 클라이언트에 전달
        return pageDto;
    }

    public void modifyItem(ItemDto itemDto) {
        itemMapper.updateItem(itemDto);
    }

    public void removeItem(int id){
        itemMapper.deleteItem(id);
    }


    // 메서드 구문
    // 접근제어자 리턴타입 메서드이름() {}
}
