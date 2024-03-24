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
    public Map<String,String> login(AccountRequestDto accountRequestDto) {
        Optional<Account> existingAccount = accountRepository.findByUsername(accountRequestDto.getUsername());
        Map<String,String> map = new HashMap<>();
        if (existingAccount.isEmpty()) {
            map.put("fail","없는 아이디입니다.");
            return map;
        }
        if(!passwordEncoder.matches(accountRequestDto.getPassword(),existingAccount.get().getPassword())){
            map.put("fail","비밀번호가 틀렸다.");
            return map;
        }else {
            map.put("success","로그인 성공"+accountRequestDto.getUsername());
        return map;
        }
    }

    @Override
    public Map<String, String> deposit(AccountRequestDto accountRequestDto) {
        Map<String,String> map = new HashMap<>();
        Optional<Account> deposit = accountRepository.findByAccountNumber(accountRequestDto.getAccountNumber());

        Account account = em.find(Account.class, deposit.get().getId());

        if(deposit.isEmpty()){
            map.put("실패","없는계좌입니다.");
        }else {
//            account.setBalance(deposit.get().getBalance() + accountRequestDto.getBalance());
            account.setBalance( accountRequestDto.getBalance());
            map.put("성공",accountRequestDto.getBalance() +"원 입금성공");
        }
        return map;
    }


}
