package com.myfruit.pms.mapper;

import com.myfruit.pms.dto.ItemDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemMapperTests {

    @Autowired
    private ItemMapper itemMapper;

    @Test
    @DisplayName("더미 상품 데이터 100개 입력")
    void insertItemTest(){
        int start = itemMapper.countTotal() +1 ;
        int end = start +100;

        String name ="과일";

        for (int i = start; i <= end; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setItem(name + i); //문자열에 숫자를 더하면 문자열로 자동형 변환

            itemMapper.insertItem(itemDto);
        }
    }
}
