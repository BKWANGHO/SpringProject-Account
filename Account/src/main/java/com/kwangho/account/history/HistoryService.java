package com.kwangho.account.history;

import com.kwangho.account.dto.request.AccountRequestDto;

import java.util.Map;

public interface HistoryService {

    Map<?,?> findAll(AccountRequestDto accountRequestDto);
}
