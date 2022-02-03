package com.liceu.practicamovies.controllers;

import com.liceu.practicamovies.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.util.UriBuilder;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String login() throws Exception {
        URL url = loginService.getGoogleRedirectURL();
        return "redirect:"+url;
    }
    //esta es la url del redirect
    @GetMapping("/oauth/oauthcallback")
    public String oauthCallback(@RequestParam String code, HttpSession session) throws Exception {
        String token = loginService.getAccessToken(code);
        Map<String,String> userDetails = loginService.getUserDetails(token);

        session.setAttribute("userDetails",userDetails);

        return "redirect:/success";
    }
    @GetMapping("/success")
    public String success(@SessionAttribute Map<String,String> userDetails, Model model){
        model.addAttribute("map",userDetails);
        return "success";
    }
}
