package com.myfruit.pms.controller;

import com.myfruit.pms.dto.ItemDto;
import com.myfruit.pms.dto.PageDto;
import com.myfruit.pms.mapper.ItemMapper;
import com.myfruit.pms.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ResponseEntity<?> createItem(@Valid @RequestBody ItemDto itemDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, List<String>> errorMap = new HashMap<>();
            // BindingResult에서 에러들을 순회
            bindingResult.getFieldErrors().forEach(error -> {
                // 에러가 발생한 필드명 추출
                String field = error.getField();
                // 해당 필드의 에러 메시지 추출
                String message = error.getDefaultMessage();

                // errorMap에 필드별 에러메시지 리스트 추가
                // computeIfAbsent: 해당 key가 없으면 새 ArrayList 생성
                // 있으면 기존 리스트에 메시지 추가
                // 참고: https://tinyurl.com/mrxbfpz8
                errorMap.computeIfAbsent(field, k -> new ArrayList<>()).add(message);
            });
            return ResponseEntity.badRequest().body(errorMap);
        }
        System.out.println(itemDto.getItem());
        itemService.createItem(itemDto);
        return ResponseEntity.ok().build();
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

    // 요청 URL 형식 : /items?page =1&size=10
    @GetMapping
    public String getItems(@RequestParam(name = "page", defaultValue = "1")int page,
                           @RequestParam(name = "limit", defaultValue = "10") int limit,
                           Model model) {
        PageDto pageDto = itemService.getItems(page,limit);
        model.addAttribute("pageDto",pageDto);

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


    @GetMapping("/{id}/remove")
    public String removeItem(@PathVariable("id") int id) {
        itemService.removeItem(id);
        return "redirect:/items";
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

