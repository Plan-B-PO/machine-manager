package com.po.planb.machinemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class MenuController {
//
//    @RequestMapping("/")
//    public String menu(@ModelAttribute(name = "supplierId") @Valid String supplierId, Model model) {
//        if (supplierId.isEmpty()) {
//            return "redirect:/login"; //TODO shouldn't put any logic here(remove when OAuth is implemented)
//        } else {
//            model.addAttribute("supplierId", supplierId);
//            return "menu";
//        }
//    }
//
//    @RequestMapping("/create")
//    public String create(@RequestParam(name = "id") @Valid String id, Model model) {
//        model.addAttribute("id", id);
//        return "createMachine";
//    }
//
//    @RequestMapping("/login")
//    public String login() {
//        return "login";
//    }

}
