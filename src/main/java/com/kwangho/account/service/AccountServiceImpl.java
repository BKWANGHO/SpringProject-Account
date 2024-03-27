package com.kwangho.account.service;

import com.kwangho.account.Enum.Messege;
import com.kwangho.account.dto.request.AccountRequestDto;
import com.kwangho.account.dto.response.AccountResponseDto;
import com.kwangho.account.model.Account;
import com.kwangho.account.repository.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {
    private final EntityManager em;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AccountResponseDto join(AccountRequestDto accountRequestDto) {
        Optional<Account> existingAccount = accountRepository.findByUsername(accountRequestDto.getUsername());
        String encodePassword = passwordEncoder.encode(accountRequestDto.getPassword());
        if (existingAccount.isPresent()) {
            return new AccountResponseDto(null, "있는 아이디 다시 입력해", null, 0);
        } else {
            Account account = Account.builder()
                    .username(accountRequestDto.getUsername())
                    .name(accountRequestDto.getName())
                    .accountNumber(accountRequestDto.getAccountNumber())
                    .password(encodePassword)
                    .balance(0)
                    .activate(true)
                    .count(0)
                    .build();
            accountRepository.save(account);
            return new AccountResponseDto(account.getName(), account.getUsername(), account.getAccountNumber(), account.getBalance());
        }
    }

    @Override
    public Map<String, Messege> login(AccountRequestDto accountRequestDto) {
        Account existingAccount = accountRepository.findByUsername(accountRequestDto.getUsername()).orElse(null);
        Map<String, Messege> map = new HashMap<>();
        if (existingAccount == null) {
            map.put("Messege", Messege.FAIL);
        } else if (existingAccount.getActivate().equals(false)) {
            map.put("Messege", Messege.ACCOUNT_LOCK);
        } else if (!passwordEncoder.matches(accountRequestDto.getPassword(), existingAccount.getPassword())) {
            map.put("Messege", Messege.FAIL);
            existingAccount.setCount(existingAccount.getCount() + 1);
            accountDisabled(existingAccount);
        } else {
            map.put("Messege", Messege.SUCCESS);
            existingAccount.setCount(0);
        }

        return map;
    }

    @Override
    public Map<String, Messege> deposit(AccountRequestDto accountRequestDto) {
        Map<String, Messege> map = new HashMap<>();
        Account deposit = accountRepository.findByAccountNumber(accountRequestDto.getAccountNumber()).orElse(null);

        if (deposit == null) {
            map.put("Messege", Messege.FAIL);
        } else {
            deposit.setBalance(deposit.getBalance() + accountRequestDto.getBalance());
            map.put("Messege", Messege.SUCCESS);
        }
        return map;
    }

    @Override
    public Map<String, Messege> withdraw(AccountRequestDto accountRequestDto) {
        Map<String, Messege> map = new HashMap<>();
        Account withdraw = accountRepository.findByAccountNumber(accountRequestDto.getAccountNumber()).orElse(null);

        if (withdraw == null) {
            map.put("Messege", Messege.FAIL);
        } else if (withdraw.getActivate().equals(false)) {
            map.put("Messege", Messege.ACCOUNT_LOCK);
        } else if (!passwordEncoder.matches(accountRequestDto.getPassword(), withdraw.getPassword())) {
            map.put("Messege", Messege.WRONG_PASSWORD);
            withdraw.setCount(withdraw.getCount() + 1);
            accountDisabled(withdraw);
        } else {
            withdraw.setBalance(withdraw.getBalance() - accountRequestDto.getBalance());
            map.put("Messege", Messege.SUCCESS);
            withdraw.setCount(0);
        }
        return map;
    }

    @Override
    public Map<String, Messege> accountTransfer(AccountRequestDto accountRequestDto) {
        Map<String, Messege> map = new HashMap<>();
        Account ac = accountRepository.findByAccountNumber(accountRequestDto.getAccountNumber()).orElse(null);
        Account rc = accountRepository.findByAccountNumber(accountRequestDto.getReceiverAccount()).orElse(null);
        if (ac == null) {
            map.put("Messege", Messege.FAIL);
        } else if (ac.getActivate().equals(false)) {
            map.put("Messege", Messege.ACCOUNT_LOCK);
        } else if (!passwordEncoder.matches(accountRequestDto.getPassword(), ac.getPassword())) {
            map.put("Messege", Messege.FAIL);
            ac.setCount(ac.getCount() + 1);
            accountDisabled(ac);
        } else if (rc == null) {
            map.put("Messege", Messege.FAIL);

        } else {
            ac.setBalance(ac.getBalance() - accountRequestDto.getBalance());
            rc.setBalance(rc.getBalance() + accountRequestDto.getBalance());
            ac.setCount(0);
            map.put("Messege", Messege.SUCCESS);
        }
        return map;
    }

    @Override
    public Map<String, Messege> accountDisabled(Account account) {
        Account accountcheck = accountRepository.findByUsername(account.getUsername()).orElse(null);
        Map<String, Messege> map = new HashMap<>();
        if (accountcheck.getCount() == 3) {
            accountcheck.setActivate(false);
            map.put("Messege", Messege.ACCOUNT_LOCK);
        }

        return map;
    }


}
