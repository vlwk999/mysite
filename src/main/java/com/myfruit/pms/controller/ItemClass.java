package com.myfruit.pms.controller;

import com.myfruit.pms.dto.ItemDto;
import com.myfruit.pms.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemClass {

    @Autowired
    private ItemMapper itemMapper;

    @GetMapping("/create")
    public String create() {
        return "shop/create-item";
    }

    @PostMapping
    public void createItem(@RequestBody ItemDto itemDto){
        //@RequestBody itemDto 가
         System.out.println(itemDto.getItem());
         itemMapper.insertItem(itemDto);
         //여기 itemDto 로 전달 그리고 itemMapper 인터페이스에 itemDto 로 전달
    }

}
