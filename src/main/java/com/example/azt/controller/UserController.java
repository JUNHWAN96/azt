package com.example.azt.controller;

import com.example.azt.dto.UserAccountDto;
import com.example.azt.service.UserService;
import com.example.azt.validation.CheckUserNameValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/auth")
@Controller
public class UserController {

    private final UserService userService;
    private final CheckUserNameValidator checkUserNameValidator; // 회원가입 중복 조회를 위해서 추가

    @InitBinder // 특정 컨트롤러에서 바인딩 또는 검증 설정을 변경하고 싶을 때 사용
    public void validatorBinder(WebDataBinder binder){ // WebDataBinder : HTTP 요청 정보를 컨트롤러 메소드의 파라미터나
        binder.addValidators(checkUserNameValidator);  // 모델에 바인딩 할 때 사용되는 바인딩 객체
    }

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
            return "auth/register";
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
