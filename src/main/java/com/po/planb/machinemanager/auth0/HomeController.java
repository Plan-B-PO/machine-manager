package com.po.planb.machinemanager.auth0;

import com.auth0.SessionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings("unused")
@Controller
public class HomeController {

    @RequestMapping(value = "/portal/home", method = RequestMethod.GET)
    protected String home(final Map<String, Object> model, final HttpServletRequest req) {
        String username = (String) SessionUtils.get(req, "username");

        if (username != null) {
            model.put("username", username);
            return "menu";
        } else {
            return "redirect:/login";
        }
    }

}