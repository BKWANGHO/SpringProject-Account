package com.kwangho.account.account.repository;

import com.kwangho.account.account.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {


    Optional<Account> findByUsername(String username);


    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findAllByUsername(String username);
}
