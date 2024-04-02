package com.kwangho.account.dto.response;

import lombok.Getter;

@Getter
public class AccountResponseDto {
    private String name;
    private String username;
    private String accountNumber;
    private int balance;

    public AccountResponseDto(String name, String username, String accountNumber, int balance) {
        this.name = name;
        this.username = username;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}
