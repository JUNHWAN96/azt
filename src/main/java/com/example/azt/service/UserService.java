package com.example.azt.service;

import com.example.azt.domain.UserAccount;
import com.example.azt.dto.UserAccountDto;

import com.example.azt.dto.security.BoardPrincipal;
import com.example.azt.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

// login 요청이 오면 자동으로 UserDetailsService 타입으로 IOC 되어 있는 loadUserByUsername 함수가 실행
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public UserAccount saveUser(UserAccountDto dto) {
        //비밀번호 암호화
        // 암호화를 해주지 않으면 시큐리티 로그인이 되지 않는다.
        dto.setPassword(encoder.encode(dto.getPassword()));

        return userAccountRepository.save(dto.toEntity());
    }


    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        UserAccount userAccount = userAccountRepository.findByUserName(userName);
//        if(userAccount !=null) {
//            UserAccountDto userAccountDto = UserAccountDto.fromEntity(userAccount);
//            BoardPrincipal userPrincipal = BoardPrincipal.from(userAccountDto);
//            return userPrincipal;
//        }
//        return null;
//    }
        // Repository에서 Optional로 받아오는 코드로 수정해서 리팩토링

        BoardPrincipal boardPrincipal = userAccountRepository.findByUserName(userName)
                .map(UserAccountDto::fromEntity)
                .map(BoardPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("해당사용자가 존재하지 않습니다: " + userName));
        return boardPrincipal;
    }

    // 회원가입 시 유효성 검사
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validateResult = new HashMap<>();

        // 유효성 검사에 실패한 필드 목록 받기
        for(FieldError error : errors.getFieldErrors()){
            String validKeyName = String.format("valid_%s", error.getField());
            validateResult.put(validKeyName, error.getDefaultMessage());
        }
        return validateResult;
    }
}