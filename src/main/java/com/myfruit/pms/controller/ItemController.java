package com.myfruit.pms.controller;

import com.myfruit.pms.dto.ItemDto;
import com.myfruit.pms.mapper.ItemMapper;
import com.myfruit.pms.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
         System.out.println(itemDto.getItem());
         itemService.createItem(itemDto);

    }

    @GetMapping("/{id}")
    public String getItem(@PathVariable("id") int id, Model model){
        // Model 객체로, 컨트롤러에서 뷰로 데이터를 전달
        try {
            ItemDto itemDto = itemService.getItem(id); // id를 사용해 아이템 정보를 가져옴
            model.addAttribute("item", itemDto); // 아이템 정보를 모델에 추가
        }catch (IllegalStateException e){
            model.addAttribute("message",e.getMessage());
            return "common/error/404";
        }
        // model.addAttribute 모델에 데이터를 추가하는 메소드 ->  데이터를 뷰로 전달
        return "shop/detail"; // shop / detail 뷰를 반환
    }

    @GetMapping
    public String getItems(Model model) {
        itemService.getItems();
        List<ItemDto> items = itemService.getItems();
        model.addAttribute("items",items);

        return "shop/list";
    }

    // modify or edit 사용
    @GetMapping("/{id}/modify")
    public String getItem2(@PathVariable("id") int id, Model model){
        // Model 객체로, 컨트롤러에서 뷰로 데이터를 전달
        try {
            ItemDto itemDto = itemService.getItem(id); // id를 사용해 아이템 정보를 가져옴
            model.addAttribute("item", itemDto); // 아이템 정보를 모델에 추가
        }catch (IllegalStateException e){
            model.addAttribute("message",e.getMessage());
            return "common/error/404";
        }
        // model.addAttribute 모델에 데이터를 추가하는 메소드 ->  데이터를 뷰로 전달
        return "shop/modify"; // shop / detail 뷰를 반환
    }

    @PostMapping("/{id}/modify")
    @ResponseBody
    public void modifyItem(@RequestBody ItemDto itemDto){
        System.out.println(itemDto.getItem());
        itemService.modifyItem(itemDto);

    }






    // 생성 페이지 GET / items /create
    // 생성 POST / items
    // 상세보기 GET / items /{id}
    // 수정페이지 GET / items/{id}/ modify
    // 수정 POST / items /{id}
    // 삭제 GET / items/{id}/ remove
    // 목록 Get /items

    // 생성 페이지 GET / items /create
    // 생성 POST / items
    // 상세보기 GET / items /{id}
    // 수정 PUT / items /{id}
    // 삭제 DELETE /items/{id}
    // 목록 Get / items
}

