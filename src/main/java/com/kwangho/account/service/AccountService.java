package com.kwangho.account.service;


import com.kwangho.account.dto.request.AccountRequestDto;
import com.kwangho.account.dto.response.AccountResponseDto;

import java.util.Map;

public interface AccountService {
    AccountResponseDto join(AccountRequestDto accountRequestDto);

    Map<String,String> login(AccountRequestDto accountRequestDto);

    Map<String, String> deposit(AccountRequestDto accountRequestDto);

    Map<String, String> withdraw(AccountRequestDto accountRequestDto);

    Map<String, String> accountTransfer(AccountRequestDto accountRequestDto);
}
