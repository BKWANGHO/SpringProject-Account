package com.kwangho.account.account.service;

import com.kwangho.account.common.Enum.Messege;
import com.kwangho.account.account.model.AccountDto;
import com.kwangho.account.account.model.Account;
import com.kwangho.account.account.repository.AccountRepository;
import com.kwangho.account.common.component.Messenger;
import com.kwangho.account.history.model.History;
import com.kwangho.account.history.repository.HistoryRepository;
import com.kwangho.account.user.repository.UserRepository;
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
    public AccountDto deposit(AccountDto dto) {
        Map<String, Messege> map = new HashMap<>();
        Account ac = accountRepository.findByAccountNumber(dto.getAccountNumber())
                .orElseThrow();

        if (ac == null) {
//      계좌가 없다. 위에서 orElseThrow 로 처리 예정

        } else if (!ac.getBank().equals(dto.getBank())) {
//        은행이 틀리다.

        } else if (!ac.getActivate()) {
//            정지계좌일때
        } else {
            ac.setTotalBalance(ac.getTotalBalance() + dto.getTotalbalance());
             historyRepository.save(History.builder()
                    .accountNumber(ac)
                    .balance(dto.getTotalbalance())
                    .totalbalance(ac.getTotalBalance())
                    .transation("입금")
                    .build());
        }
        return AccountDto.builder()
                .id(ac.getId())
                .accountNumber(ac.getAccountNumber())
                .totalbalance(ac.getTotalBalance())
                .user(ac.getUser().getUsername())
                .build();
    }

    @Override
    public AccountDto withdraw(AccountDto dto) {
        Account ac = accountRepository.findByAccountNumber(dto.getAccountNumber()).orElse(null);


        if (ac == null) {
//      계좌 없을 시 위에서 처리예정
        } else if (!ac.getBank().equals(dto.getBank())) {
//            은행 틀렸을시 exception 처리
        } else if (!ac.getActivate()) {
//            정지계좌일 시 exception 처리
        } else if (!passwordEncoder.matches(dto.getAccountPassword(), ac.getAccountPassword())) {
//            비밀번호 틀릴시 exception 처리
            ac.setCount(ac.getCount() + 1);
            accountDisabled(dto);
        } else {
            ac.setTotalBalance(ac.getTotalBalance() - dto.getTotalbalance());
            historyRepository.save(History.builder()
                    .accountNumber(ac)
                    .balance(dto.getTotalbalance())
                    .totalbalance(ac.getTotalBalance())
                    .transation("출금")
                    .build());
            ac.setCount(0);
        }
        return AccountDto.builder()
                .id(ac.getId())
                .accountNumber(ac.getAccountNumber())
                .totalbalance(ac.getTotalBalance())
                .user(ac.getUser().getUsername())
                .build();
    }


    @Override
    public AccountDto accountTransfer(AccountDto dto) {
        Map<String, Messege> map = new HashMap<>();
        Account ac = accountRepository.findByAccountNumber(dto.getAccountNumber()).orElse(null);
        Account rc = accountRepository.findByAccountNumber(dto.getReceiverAccount()).orElse(null);

        if (ac == null) {
            map.put("Messege", Messege.FAIL);
        } else if (!ac.getBank().equals(dto.getBank())) {
            map.put("Messege", Messege.FAIL);
        } else if (ac.getActivate().equals(false)) {
            map.put("Messege", Messege.ACCOUNT_LOCK);
        } else if (!passwordEncoder.matches(dto.getAccountPassword(), ac.getAccountPassword())) {
            map.put("Messege", Messege.FAIL);
            ac.setCount(ac.getCount() + 1);
            accountDisabled(dto);
        } else if (rc == null) {
            map.put("Messege", Messege.FAIL);
        } else if (!rc.getBank().equals(dto.getReceiverbank())) {
            map.put("Messege", Messege.FAIL);

        } else {
            ac.setTotalBalance(ac.getTotalBalance() - dto.getTotalbalance());
            rc.setTotalBalance(rc.getTotalBalance() + dto.getTotalbalance());
            historyRepository.save(History.builder()
                    .accountNumber(ac)
                    .balance(dto.getTotalbalance())
                    .totalbalance(ac.getTotalBalance())
                    .transation("송금")
                    .build());

            historyRepository.save(History.builder()
                    .accountNumber(rc)
                    .balance(dto.getTotalbalance())
                    .totalbalance(rc.getTotalBalance())
                    .transation("송금")
                    .build());

            ac.setCount(0);
            map.put("Messege", Messege.SUCCESS);
        }
        return map;
    }

    @Override
    public Messenger accountDisabled(AccountDto dto) {
        Account ac = accountRepository.findByAccountNumber(dto.getAccountNumber()).orElse(null);
        if (ac.getCount() == 3) {
            ac.setActivate(false);
        }
        return Messenger.builder()
                .message("정지계좌입니다.")
                .build();
    }


    @Override
    public Messenger save(AccountDto accountDto) {
        String encodePassword = passwordEncoder.encode(accountDto.getAccountPassword());
        if (userRepository.findByUsername(accountDto.getUser()).isEmpty()) {
            return Messenger.builder()
                    .message("FAILURE")
                    .build();
        } else {
            Account account = Account.builder()
                    .user(userRepository.findByUsername(accountDto.getUser()).get())
                    .bank((accountDto.getBank()))
                    .accountNumber(accountDto.getAccountNumber())
                    .accountPassword(encodePassword)
                    .totalBalance(0)
                    .activate(true)
                    .count(0)
                    .build();
            accountRepository.save(account);
            return Messenger.builder()
                    .message("SUCCESS")
                    .build();
        }
    }

    @Override
    public Messenger deleteById(Long id) {
        return null;
    }

    @Override
    public Messenger modify(AccountDto accountDto) {
        return null;
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(i->AccountDto.builder()
                        .accountPassword(i.getAccountPassword())
                        .accountNumber(i.getAccountNumber())
                        .user(i.getUser().getUsername())
                        .id(i.getId())
                        .activate(i.getActivate())
                        .build())
                .toList();
    }

    @Override
    public Optional<AccountDto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }
}
