package com.david.ecommercecrm.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String home(@AuthenticationPrincipal UserDetails userDetails, Model model){
        String username = userDetails.getUsername();

        String name = (username.isEmpty()) ? username : "anonymous";

        model.addAttribute("username",name);

        return "home";
    }

}
