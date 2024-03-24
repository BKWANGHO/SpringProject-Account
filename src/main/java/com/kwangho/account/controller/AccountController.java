package com.kwangho.account.controller;

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
    public Map<String,String> login(@RequestBody AccountRequestDto accountRequestDto){
        return accountService.login(accountRequestDto);
    }

    @PostMapping("/deposit")
    public Map<String,String> deposit(@RequestBody AccountRequestDto accountRequestDto){
        System.out.println(accountRequestDto.getBalance());
        return accountService.deposit(accountRequestDto);
    }
}
