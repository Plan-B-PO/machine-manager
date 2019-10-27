package com.po.planb.machinemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {

    @RequestMapping("/")
    public String menu(Model model) {
        model.addAttribute("supplierId", 123);
        return "menu";
    }

    @RequestMapping("/create")
    public String create() {
        return "createMachine";
    }
}
