package com.kwangho.account.service;

import com.kwangho.account.dto.request.AccountRequestDto;
import com.kwangho.account.model.History;
import com.kwangho.account.repository.HistoryRepository;
import com.kwangho.account.repository.mapping.HistoryMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService{
    private final HistoryRepository historyRepository;


    @Override
    public Map<?, ?> findAll(AccountRequestDto accountRequestDto) {
        List<History> list = historyRepository.findAllByAccountNumber(accountRequestDto.getAccountNumber());
        System.out.println(list);
        Map<String,List> map = new HashMap<>();
        map.put("SUCCESS",list);

        return map;
    }
}
