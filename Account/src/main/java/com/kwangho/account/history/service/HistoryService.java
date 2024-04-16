package com.kwangho.account.history.service;

import com.kwangho.account.account.model.AccountDto;

import java.util.Map;

public interface HistoryService {

    Map<?,?> findAll(AccountDto accountRequestDto);
}
