package com.example.azt.domain;

import com.example.azt.domain.constant.UserType;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
public class UserAccount {

    @Id
    @Column(length = 50, name="username")
    private String userName;

    @Column(nullable = false) private String password;

    @Column(length = 100) private String email;

    @Column(length = 100) private String address;

    @Column(length = 100) private String nickname;

    @Enumerated(EnumType.STRING)
    private UserType role;

    protected UserAccount(){};

    @Builder
    public UserAccount(String userName, String password, String email, String address, String nickName, UserType role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.nickname = nickName;
        this.role = role;
    }

}
