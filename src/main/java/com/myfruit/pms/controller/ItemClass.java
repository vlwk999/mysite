package com.myfruit.pms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemClass {

    @GetMapping("/create")
    public String create() {
        return "shop/create-item";
    }
}
