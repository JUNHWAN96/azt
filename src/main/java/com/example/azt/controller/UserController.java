package com.example.azt.controller;

import com.example.azt.dto.UserAccountDto;
import com.example.azt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/auth")
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String registerForm(){
        return "auth/register";
    }

    // 유효성 검사를 위해 @Valid 어노테이션 추가, Errors를 받아줬다
    @PostMapping("/register")
    public String register(@Valid UserAccountDto userAccountDto, Errors errors, Model model){

        if(errors.hasErrors()){
            // 회원가입 실패시 입력 데이터 값 유지
            model.addAttribute("userAccountDto", userAccountDto);

            Map<String,String> validatorResult = userService.validateHandling(errors);
            for(String key : validatorResult.keySet()){ // Map을 전체 순회하기 위해서 keySet()사용/ key값만 가져옴
                model.addAttribute(key,validatorResult.get(key));
            }
            return "/auth/register";
        }

        userService.saveUser(userAccountDto);

        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String loginForm(@RequestParam(value = "error", required = false)String error,
                            @RequestParam(value = "exception", required = false)String exception,
                            Model model){

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "auth/login";
    }

}
