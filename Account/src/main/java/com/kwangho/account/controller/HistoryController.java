package com.kwangho.account.controller;


import com.kwangho.account.dto.request.AccountRequestDto;
import com.kwangho.account.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class HistoryController {
    private final HistoryService historyService;


@PostMapping("/history")
    public Map<?,?> findAll (@RequestBody AccountRequestDto accountRequestDto){
        return historyService.findAll(accountRequestDto);


    }


}
