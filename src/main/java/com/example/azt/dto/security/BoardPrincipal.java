package com.example.azt.dto.security;

import com.example.azt.domain.constant.UserType;
import com.example.azt.dto.UserAccountDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인 진행
// 로그인 진행이 완료가 되면 session을 만들어 준다. (Security ContextHolder)
// 오브젝트 타입 => Authentication 타입 객체
// Authentication 안에 User 정보가 있어야 한다.
// User 오브젝트타입 => UserDetails 타입 객체
@Getter
public class BoardPrincipal implements UserDetails {

    private String userName;
    private String password;
    private String email;
    private String address;
    private String nickName;
    private UserType role;



    @Override public String getUsername() {return userName;}
    @Override public String getPassword() {return password;}

    // 해당 User의 권한을 리턴
    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>(); // String으로 반환 할 수 없기 떄문에 여기 담아준다
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return String.valueOf(role);
            }
        });
        return collect;}

    @Override public boolean isAccountNonExpired() {return true;}
    @Override public boolean isAccountNonLocked() {return true;}
    @Override public boolean isCredentialsNonExpired() {return true;}
    @Override public boolean isEnabled() {return true;}

    public BoardPrincipal(String userName, String password, String email, String address, String nickName, UserType role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.nickName = nickName;
        this.role =role;
    }

    public static BoardPrincipal of(String userName,String password,String email, String address, String nickName, UserType role){

        return new BoardPrincipal(
                userName,
                password,
                email,
                address,
                nickName,
                role
        );
    }

    public static BoardPrincipal from(UserAccountDto dto){
        return BoardPrincipal.of(
                dto.getUserName(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getNickName(),
                dto.getRole()
        );
    }

    public UserAccountDto toDto(){
        return UserAccountDto.of(
                userName,
                password,
                email,
                address,
                nickName,
                role
        );
    }



}
