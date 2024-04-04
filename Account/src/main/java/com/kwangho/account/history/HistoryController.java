package com.kwangho.account.history;


import com.kwangho.account.account.AccountRequestDto;
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
