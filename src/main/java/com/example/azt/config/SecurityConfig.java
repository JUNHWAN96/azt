package com.example.azt.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    // 개발단계이기 때문에 security 권한을 모두 풀어줌.
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http    .csrf().disable()
                        .authorizeRequests()
                        .anyRequest().permitAll()
                        .and()
                    .formLogin()
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login") // post 요청시 보낼 경로
                        .defaultSuccessUrl("/test")
                        .permitAll()
                        .and()
                    .logout()
                        .permitAll();
        }

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }


        // 해시 함수를 이용한 암호화 처리
        @Bean
        public BCryptPasswordEncoder encoder() {
            return new BCryptPasswordEncoder();
        }

}
