package com.kwangho.account.account;

import com.kwangho.account.account.Account;
import com.kwangho.account.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(User username);

    Optional<Account> findByAccountNumber(String accountNumber);

    List<Account> findAllByUsername(User username);
}
