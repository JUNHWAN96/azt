package com.example.azt.service;

import com.example.azt.domain.UserAccount;
import com.example.azt.dto.UserAccountDto;
import com.example.azt.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public UserAccount saveUser(UserAccountDto dto){
        //비밀번호 암호화
        dto.setPassword(encoder.encode(dto.getPassword()));

        return userAccountRepository.save(dto.toEntity());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserAccount userAccount = userAccountRepository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다."));

        return new UserAccountDto(userAccount.getUserName(),
                                  userAccount.getPassword(),
                                  userAccount.getEmail(),
                                  userAccount.getAddress(),
                                  userAccount.getAddress(),
                                  userAccount.getRole());
    }
}
