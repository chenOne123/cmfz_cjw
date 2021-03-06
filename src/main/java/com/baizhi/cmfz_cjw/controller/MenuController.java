package com.baizhi.cmfz_cjw.controller;

import com.baizhi.cmfz_cjw.entity.Menu;
import com.baizhi.cmfz_cjw.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("/getAll")
    public List<Menu> getAll(){
        System.out.println("进来了");
        return menuService.getAll();
    }
}
