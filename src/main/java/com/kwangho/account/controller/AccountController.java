package com.kwangho.account.controller;

import com.kwangho.account.Enum.Messege;
import com.kwangho.account.dto.request.AccountRequestDto;
import com.kwangho.account.dto.response.AccountResponseDto;
import com.kwangho.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("http://localhost:3000")
@RestController
//@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;



    @PostMapping("/join")
    public AccountResponseDto join(@RequestBody AccountRequestDto accountRequestDto) {
        return accountService.join(accountRequestDto);
    }

    @PostMapping("/login")
    public Map<String, Messege> login(@RequestBody AccountRequestDto accountRequestDto){
        return accountService.login(accountRequestDto);
    }

    @PostMapping("/deposit")
    public Map<String,Messege> deposit(@RequestBody AccountRequestDto accountRequestDto){
        return accountService.deposit(accountRequestDto);
    }

    @PostMapping("/withdraw")
    public Map<String,Messege> withdraw(@RequestBody AccountRequestDto accountRequestDto){
        return accountService.withdraw(accountRequestDto);
    }

    @PostMapping("/transfer")
    public Map<String,Messege> accountTransfer(@RequestBody AccountRequestDto accountRequestDto){
        return accountService.accountTransfer(accountRequestDto);
    }

}
