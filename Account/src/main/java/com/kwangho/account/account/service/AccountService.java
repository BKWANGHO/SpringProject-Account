package com.kwangho.account.account.service;


import com.kwangho.account.common.Enum.Messege;
import com.kwangho.account.account.model.AccountDto;
import com.kwangho.account.common.component.Messenger;
import com.kwangho.account.common.service.CommandService;
import com.kwangho.account.common.service.QueryService;

import java.util.List;
import java.util.Map;


public interface AccountService extends CommandService<AccountDto>, QueryService<AccountDto> {


    AccountDto deposit(AccountDto accountRequestDto);

    AccountDto withdraw(AccountDto accountRequestDto);

    AccountDto accountTransfer(AccountDto accountRequestDto);

    Messenger accountDisabled(AccountDto accountRequestDto);

}
