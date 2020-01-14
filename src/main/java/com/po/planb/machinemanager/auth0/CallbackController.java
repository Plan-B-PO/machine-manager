package com.po.planb.machinemanager.auth0;

import com.auth0.IdentityVerificationException;
import com.auth0.SessionUtils;
import com.auth0.Tokens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("unused")
@Controller
public class CallbackController {

    @Autowired
    private AuthController controller;
    private final String redirectOnFail;
    private final String redirectOnSuccess;
    private final SupplierService supplierService;

    public CallbackController(SupplierService supplierService) {
        this.supplierService = supplierService;
        this.redirectOnFail = "/login";
        this.redirectOnSuccess = "/portal/home";
    }

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    protected void getCallback(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        handle(request, response);
    }

    @RequestMapping(value = "/callback", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    protected void postCallback(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        handle(request, response);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Tokens tokens = controller.handle(request, response);
            String accessToken = tokens.getAccessToken();
            SessionUtils.set(request, "accessToken", accessToken);
            String idToken = tokens.getIdToken();
            SessionUtils.set(request, "idToken", idToken);
            SessionUtils.set(request, "username", supplierService.getUserInfo(accessToken, idToken));
            response.sendRedirect(redirectOnSuccess);
        } catch (IdentityVerificationException e) {
            e.printStackTrace();
            response.sendRedirect(redirectOnFail);
        }
    }

}