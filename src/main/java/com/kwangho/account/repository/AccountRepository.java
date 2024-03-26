package com.kwangho.account.repository;

import com.kwangho.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);

    Optional<Account> findByAccountNumber(String accountNumber);

}
