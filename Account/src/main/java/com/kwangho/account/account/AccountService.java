package com.kwangho.account.account;


import com.kwangho.account.Enum.Messege;
import com.kwangho.account.common.ResponseMessege;

import java.util.Map;

public interface AccountService {
    ResponseMessege join(AccountRequestDto accountRequestDto);


    Map<String, Messege> deposit(AccountRequestDto accountRequestDto);

    Map<String, Messege> withdraw(AccountRequestDto accountRequestDto);

    Map<String, Messege> accountTransfer(AccountRequestDto accountRequestDto);

    Map<String,Messege> accountDisabled(AccountRequestDto accountRequestDto);

    Map<String, Object> accountList(AccountRequestDto accountRequestDto);
}
