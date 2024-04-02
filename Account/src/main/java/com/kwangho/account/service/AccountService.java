package com.kwangho.account.service;


import com.kwangho.account.Enum.Messege;
import com.kwangho.account.dto.request.AccountRequestDto;
import com.kwangho.account.dto.response.AccountResponseDto;
import com.kwangho.account.model.Account;

import java.util.Map;

public interface AccountService {
    AccountResponseDto join(AccountRequestDto accountRequestDto);

    Map<String, Messege> login(AccountRequestDto accountRequestDto);

    Map<String, Messege> deposit(AccountRequestDto accountRequestDto);

    Map<String, Messege> withdraw(AccountRequestDto accountRequestDto);

    Map<String, Messege> accountTransfer(AccountRequestDto accountRequestDto);

    Map<String,Messege> accountDisabled(Account account);

    Map<String, Object> accountList(AccountRequestDto accountRequestDto);
}
