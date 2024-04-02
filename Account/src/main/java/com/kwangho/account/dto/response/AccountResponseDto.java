package com.kwangho.account.dto.response;

import com.kwangho.account.user.User;
import lombok.Getter;

@Getter
public class AccountResponseDto {
    private String name;
    private User username;
    private String accountNumber;
    private int balance;

    public AccountResponseDto(String name, User username, String accountNumber, int balance) {
        this.name = name;
        this.username = username;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
