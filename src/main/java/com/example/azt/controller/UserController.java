package com.example.azt.controller;

import com.example.azt.dto.UserAccountDto;
import com.example.azt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/auth")
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerForm(){
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(UserAccountDto userAccountDto){

        userService.saveUser(userAccountDto);

        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "auth/login";
    }

}
