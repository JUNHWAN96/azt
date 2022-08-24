package com.example.azt.dto;

import com.example.azt.domain.UserAccount;
import com.example.azt.domain.constant.UserType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;

@Getter
@Setter
@Builder
public class UserAccountDto implements UserDetails {

    @NotBlank(message = "아이디를 입력하세요")
    private String userName;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;
    @NotBlank(message = "이메일을 입력하세요")
    @Email(message = "올바른 이메일 형식을 입력하세요")
    private String email;
    private String address;
    private String nickName;
    private UserType role;

    public UserAccountDto(String userName, String password, String email, String address, String nickName, UserType role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.nickName = nickName;
        this.role = role;
    }

    // Dto를 entity로 변환
    public UserAccount toEntity(){
        UserAccount userAccount = UserAccount.builder()
                                  .userName(userName)
                                  .password(password)
                                  .email(email)
                                  .address(address)
                                  .nickName(nickName)
                                  .role(role).build();
        return userAccount;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
