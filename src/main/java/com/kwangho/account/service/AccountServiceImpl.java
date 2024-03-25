package com.kwangho.account.service;

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
                    .build();

            accountRepository.save(account);

            return new AccountResponseDto(account.getName(), account.getUsername(), account.getAccountNumber(), account.getBalance());
        }
    }

    @Override
    public Map<String, String> login(AccountRequestDto accountRequestDto) {
        Account existingAccount = accountRepository.findByUsername(accountRequestDto.getUsername()).orElse(null);
        Map<String, String> map = new HashMap<>();
        if (existingAccount == null) {
            map.put("Messege", "없는 아이디입니다.");
        } else if (!passwordEncoder.matches(accountRequestDto.getPassword(), existingAccount.getPassword())) {
            map.put("Messege", "비밀번호가 틀렸다.");
        } else {
            map.put("Messege", "로그인 성공" + accountRequestDto.getUsername());
        }
        return map;
    }

    @Override
    public Map<String, String> deposit(AccountRequestDto accountRequestDto) {
        Map<String, String> map = new HashMap<>();
        Account deposit = accountRepository.findByAccountNumber(accountRequestDto.getAccountNumber()).orElse(null);

        if (deposit == null) {
            map.put("Messege", "없는계좌입니다.");
        } else {
            deposit.setBalance(deposit.getBalance() + accountRequestDto.getBalance());
            map.put("Messege", accountRequestDto.getBalance() + "원 입금성공");
        }
        return map;
    }

    @Override
    public Map<String, String> withdraw(AccountRequestDto accountRequestDto) {
        Map<String, String> map = new HashMap<>();
        Account withdraw = accountRepository.findByAccountNumber(accountRequestDto.getAccountNumber()).orElse(null);

        if (withdraw == null) {
            map.put("Messege", "없는계좌입니다.");
        } else {
            withdraw.setBalance(withdraw.getBalance() - accountRequestDto.getBalance());
            map.put("Messege", accountRequestDto.getBalance() + "원 입금성공");
        }
        return map;
    }

    @Override
    public Map<String, String> accountTransfer(AccountRequestDto accountRequestDto) {
        Map<String, String> map = new HashMap<>();
        Account ac = accountRepository.findByAccountNumber(accountRequestDto.getAccountNumber()).orElse(null);
        Account rc = accountRepository.findByAccountNumber(accountRequestDto.getReceiverAccount()).orElse(null);
        if (ac == null) {
            map.put("Messege", "출금 계좌를 확인하세요");
        } else if (!passwordEncoder.matches(accountRequestDto.getPassword(),ac.getPassword())) {
            map.put("Messege","비밀번호를 확인하세요");
        } else if (rc == null) {
            map.put("Messege", "입금 계좌를 확인하세요");
        } else {
            ac.setBalance(ac.getBalance() - accountRequestDto.getBalance());
            rc.setBalance(rc.getBalance() + accountRequestDto.getBalance());
            map.put("Messege", accountRequestDto.getBalance() + "원 송금성공");

        }
        return map;
    }
}
