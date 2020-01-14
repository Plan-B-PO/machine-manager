package com.po.planb.machinemanager.controller;

import com.auth0.SessionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class MenuController {

    @RequestMapping("/")
    public String menu(final Map<String, Object> model, final HttpServletRequest req) {
        String username = (String) SessionUtils.get(req, "username");
        if (username == null) {
            return "redirect:/login"; //TODO shouldn't put any logic here(remove when OAuth is implemented)
        } else {
            model.put("username", username);
            return "redirect:/portal/home";
        }
    }

    @RequestMapping("/create")
    public String create(final Map<String, Object> model, final HttpServletRequest req) {
        String username = (String) SessionUtils.get(req, "username");
        model.put("id", username);
        return "createMachine";
    }
}
