package com.example.azt.dto;

import com.example.azt.domain.UserAccount;
import com.example.azt.domain.constant.UserType;
import lombok.Getter;

@Getter
public class UserSessionDto {

    private String userName;
    private String password;
    private String email;
    private String address;
    private String nickName;
    private UserType role;

    // Entity 를 Dto로 변환
    public UserSessionDto(UserAccount userAccount){
        this.userName = userAccount.getUserName();
        this.password = userAccount.getPassword();
        this.email = userAccount.getEmail();
        this.address = userAccount.getAddress();
        this.role = userAccount.getRole();
    }



}
