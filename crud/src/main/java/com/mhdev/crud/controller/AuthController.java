package com.mhdev.crud.controller;

import com.mhdev.crud.entity.AppUser;
import com.mhdev.crud.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
@Autowired
private AppUserDetailsService appUserDetailsService;
    @GetMapping("/login")
    public String login() {
        return "login-page";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new AppUser());
        return "registration-page";
    }
    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") AppUser user) {
        appUserDetailsService.createUser(user);
        return "redirect:/login";
    }


}
