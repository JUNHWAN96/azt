package com.example.azt.validation;

import com.example.azt.dto.UserAccountDto;
import com.example.azt.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@RequiredArgsConstructor
@Component
public class CheckUserNameValidator extends AbstractValidator<UserAccountDto> {

    private final UserAccountRepository userAccountRepository;

    @Override
    protected void doValidate(UserAccountDto dto, Errors errors) {
        if(userAccountRepository.existsByUserName(dto.toEntity().getUserName())){
            errors.rejectValue("userName", "아이디 중복 오류", "이미 사용중인 아이디 입니다");
        }
    }
}
