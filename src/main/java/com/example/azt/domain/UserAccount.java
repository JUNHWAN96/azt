package com.example.azt.domain;

import com.example.azt.domain.constant.UserType;
import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
public class UserAccount {

    @Id
    @Column(length = 50)
    private String userId;

    @Column(nullable = false) private String password;

    @Column(length = 100) private String email;

    @Column(length = 100) private String address;

    @Column(length = 100) private String nickName;

    @Enumerated(EnumType.STRING)
    private UserType role;

    protected UserAccount(){};

    public UserAccount(String userId, String password, String email, String address, String nickName, UserType role) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.address = address;
        this.nickName = nickName;
        this.role = role;
    }


}
