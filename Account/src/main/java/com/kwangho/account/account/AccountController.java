package com.kwangho.account.account;

import com.kwangho.account.account.model.AccountDto;
import com.kwangho.account.account.service.AccountService;
import com.kwangho.account.common.component.Messenger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class AccountController {
    private final AccountService service;



    @PostMapping("/save")
    public ResponseEntity<Messenger> save(@RequestBody AccountDto accountRequestDto) {
        return ResponseEntity.ok(service.save(accountRequestDto));
    }


    @PostMapping("/deposit")
    public ResponseEntity<AccountDto> deposit(@RequestBody AccountDto accountRequestDto){
        return ResponseEntity.ok(service.deposit(accountRequestDto));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<AccountDto> withdraw(@RequestBody AccountDto accountRequestDto){
        return ResponseEntity.ok(service.withdraw(accountRequestDto));
    }

    @PostMapping("/transfer")
    public ResponseEntity<AccountDto> accountTransfer(@RequestBody AccountDto accountRequestDto){
        return ResponseEntity.ok(service.accountTransfer(accountRequestDto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<AccountDto>> accountList(){
        return ResponseEntity.ok(service.findAll());
    }
}
