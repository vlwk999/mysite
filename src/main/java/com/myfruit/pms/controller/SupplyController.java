package com.myfruit.pms.controller;

import com.myfruit.pms.dto.SupplyDto;
import com.myfruit.pms.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/supply")
public class SupplyController {

    @Autowired
    private SupplyService supplyService;

    @GetMapping("/create")
    public String create(){
        return "shop/supply/create-supply";
    }

    @PostMapping
    @ResponseBody
    public void createSupply(@RequestBody SupplyDto supplyDto){
        supplyService.createSupply(supplyDto);
    }
}
