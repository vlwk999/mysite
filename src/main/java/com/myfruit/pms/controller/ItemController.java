package com.myfruit.pms.controller;

import com.myfruit.pms.dto.ItemDto;
import com.myfruit.pms.mapper.ItemMapper;
import com.myfruit.pms.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getItem(@PathVariable("id") int id, Model model){
        ItemDto itemDto = itemService.getItem(id);
        model.addAttribute("item", itemDto);

        return "shop/detail";
    }

    // 생성 페이지 GET / items /create
    // 생성 POST / items
    // 상세보기 GET / items /{id}
    // 수정 POST / items /{id}
    // 삭제 GET / items/{id}/delete

    // 생성 페이지 GET / items /create
    // 생성 POST / items
    // 상세보기 GET / items /{id}
    // 수정 PUT / items /{id}
    // 삭제 DELETE /items/{id}

}

