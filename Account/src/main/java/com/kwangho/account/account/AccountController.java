package com.kwangho.account.account;

import com.kwangho.account.Enum.Messege;
import com.kwangho.account.common.ResponseMessege;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("http://localhost:3000")
@RestController
//@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;



    @PostMapping("/account/join")
    public ResponseMessege join(@RequestBody AccountRequestDto accountRequestDto) {
        return accountService.join(accountRequestDto);
    }


    @PostMapping("/deposit")
    public Map<String,Messege> deposit(@RequestBody AccountRequestDto accountRequestDto){
        return accountService.deposit(accountRequestDto);
    }

    @PostMapping("/withdraw")
    public Map<String,Messege> withdraw(@RequestBody AccountRequestDto accountRequestDto){
        return accountService.withdraw(accountRequestDto);
    }

    @PostMapping("/transfer")
    public Map<String,Messege> accountTransfer(@RequestBody AccountRequestDto accountRequestDto){
        return accountService.accountTransfer(accountRequestDto);
    }

    @PostMapping("/account/list")
    public Map<String, Object> accountList(@RequestBody AccountRequestDto accountRequestDto){
        return accountService.accountList(accountRequestDto);
    }
}
