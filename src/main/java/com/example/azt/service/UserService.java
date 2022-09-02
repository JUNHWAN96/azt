package com.example.azt.service;

import com.example.azt.domain.UserAccount;
import com.example.azt.dto.UserAccountDto;

import com.example.azt.dto.security.BoardPrincipal;
import com.example.azt.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// login 요청이 오면 자동으로 UserDetailsService 타입으로 IOC 되어 있는 loadUserByUsername 함수가 실행
@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public UserAccount saveUser(UserAccountDto dto){
        //비밀번호 암호화
        // 암호화를 해주지 않으면 시큐리티 로그인이 되지 않는다.
        dto.setPassword(encoder.encode(dto.getPassword()));

        return userAccountRepository.save(dto.toEntity());
    }


    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByUserName(userName);
        if(userAccount !=null) {
            UserAccountDto userAccountDto = UserAccountDto.fromEntity(userAccount);
            BoardPrincipal userPrincipal = BoardPrincipal.from(userAccountDto);
            return userPrincipal;
        }
        return null;
    }
}
