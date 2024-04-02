package com.kwangho.account.service;

import com.kwangho.account.dto.request.AccountRequestDto;

import java.util.Map;

public interface HistoryService {

    Map<?,?> findAll(AccountRequestDto accountRequestDto);
}
