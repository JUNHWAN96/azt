package com.example.azt.repository;

import com.example.azt.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccount, String> {

    UserAccount findByUserName(String userName);

}
