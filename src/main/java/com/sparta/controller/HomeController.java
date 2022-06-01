package com.sparta.controller;

import com.sparta.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

            model.addAttribute("username", userDetails.getUsername());
            return "index";
        }


    @GetMapping("/")
    public String home1() {
        return "redirect:/index.html";
    }
    @GetMapping("/api/get/memos/get")
    public String home2() {
        return "redirect:/detail.html";
    }

}