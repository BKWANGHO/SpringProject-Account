package com.kwangho.account.account;

import com.kwangho.account.Enum.Messege;
import com.kwangho.account.common.ResponseMessege;
import com.kwangho.account.dto.request.AccountRequestDto;
import com.kwangho.account.dto.response.AccountResponseDto;
import com.kwangho.account.history.History;
import com.kwangho.account.history.HistoryRepository;
import com.kwangho.account.user.User;
import com.kwangho.account.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final HistoryRepository historyRepository;

    @Override
    public ResponseMessege join(AccountRequestDto accountRequestDto) {
        String encodePassword = passwordEncoder.encode(accountRequestDto.getPassword());
        if (userRepository.existsById(accountRequestDto.getUserId())) {
            return new ResponseMessege("messege",Messege.FAIL);
        } else {
            Account account = Account.builder()
                    .user(userRepository.findById(accountRequestDto.getUserId()).get())
                    .name(accountRequestDto.getName())
                    .bank((accountRequestDto.getBank()))
                    .accountNumber(accountRequestDto.getAccountNumber())
                    .password(encodePassword)
                    .totalBalance(0)
                    .activate(true)
                    .count(0)
                    .build();
            accountRepository.save(account);
            return new ResponseMessege("messege",Messege.SUCCESS);
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
            accountDisabled(accountRequestDto);
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
        } else if (!deposit.getBank().equals(accountRequestDto.getBank())) {
            map.put("Messege", Messege.FAIL);
        } else {
            deposit.setTotalBalance(deposit.getTotalBalance() + accountRequestDto.getTotalbalance());
            historyRepository.save(History.builder()
                    .accountNumber(deposit)
                    .balance(accountRequestDto.getTotalbalance())
                    .totalbalance(deposit.getTotalBalance())
                    .transation("입금")
                    .build());
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
        } else if (!withdraw.getBank().equals(accountRequestDto.getBank())) {
            map.put("Messege", Messege.FAIL);
        } else if (withdraw.getActivate().equals(false)) {
            map.put("Messege", Messege.ACCOUNT_LOCK);
        } else if (!passwordEncoder.matches(accountRequestDto.getPassword(), withdraw.getPassword())) {
            map.put("Messege", Messege.WRONG_PASSWORD);
            withdraw.setCount(withdraw.getCount() + 1);
            accountDisabled(accountRequestDto);
        } else {
            withdraw.setTotalBalance(withdraw.getTotalBalance() - accountRequestDto.getTotalbalance());
            historyRepository.save(History.builder()
                    .accountNumber(withdraw)
                    .balance(accountRequestDto.getTotalbalance())
                    .totalbalance(withdraw.getTotalBalance())
                    .transation("출금")
                    .build());
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
        } else if (!ac.getBank().equals(accountRequestDto.getBank())) {
            map.put("Messege", Messege.FAIL);
        } else if (ac.getActivate().equals(false)) {
            map.put("Messege", Messege.ACCOUNT_LOCK);
        } else if (!passwordEncoder.matches(accountRequestDto.getPassword(), ac.getPassword())) {
            map.put("Messege", Messege.FAIL);
            ac.setCount(ac.getCount() + 1);
            accountDisabled(accountRequestDto);
        } else if (rc == null) {
            map.put("Messege", Messege.FAIL);
        } else if (!rc.getBank().equals(accountRequestDto.getReceiverbank())) {
            map.put("Messege", Messege.FAIL);

        } else {
            ac.setTotalBalance(ac.getTotalBalance() - accountRequestDto.getTotalbalance());
            rc.setTotalBalance(rc.getTotalBalance() + accountRequestDto.getTotalbalance());
            historyRepository.save(History.builder()
                    .accountNumber(ac)
                    .balance(accountRequestDto.getTotalbalance())
                    .totalbalance(ac.getTotalBalance())
                    .transation("송금")
                    .build());

            historyRepository.save(History.builder()
                    .accountNumber(rc)
                    .balance(accountRequestDto.getTotalbalance())
                    .totalbalance(rc.getTotalBalance())
                    .transation("송금")
                    .build());

            ac.setCount(0);
            map.put("Messege", Messege.SUCCESS);
        }
        return map;
    }

    @Override
    public Map<String, Messege> accountDisabled(AccountRequestDto accountRequestDto) {
        Account accountcheck = accountRepository.findByUsername(accountRequestDto.getUsername()).orElse(null);
        Map<String, Messege> map = new HashMap<>();
        if (accountcheck.getCount() == 3) {
            accountcheck.setActivate(false);
            map.put("Messege", Messege.ACCOUNT_LOCK);
        }
        return map;
    }

    @Override
    public Map<String, Object> accountList(AccountRequestDto accountRequestDto) {
        Map<String, Object> map = new HashMap<>();
        List<Account> ls = accountRepository.findAllByUsername(accountRequestDto.getUsername());
        map.put("Messege",Messege.SUCCESS);
        map.put("result",ls);
        return map;
    }


}
