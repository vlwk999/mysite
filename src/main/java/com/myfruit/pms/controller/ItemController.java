package com.myfruit.pms.controller;

import com.myfruit.pms.dto.ItemDto;
import com.myfruit.pms.mapper.ItemMapper;
import com.myfruit.pms.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/items")
public class ItemController {


    @Autowired
    private ItemService itemService;

    @GetMapping("/create")
    public String create() {
        return "shop/create-item";
    }

    @PostMapping
    @ResponseBody
    public void createItem(@RequestBody ItemDto itemDto){
        //@RequestBody itemDto 가
         System.out.println(itemDto.getItem());
         itemService.createItem(itemDto);
         //여기 itemDto 로 전달 그리고 itemMapper 인터페이스에 itemDto 로 전달
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ItemDto getItem(@PathVariable("id") int id){
        return itemService.getItem(id);
    }

}
