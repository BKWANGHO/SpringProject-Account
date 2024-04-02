package com.kwangho.account.dto.request;

import lombok.Getter;

@Getter
public class AccountRequestDto {

    private String username;
    private String name;
    private String password;
    private String accountNumber;
    private int totalbalance;
    private String receiverAccount;
    private String bank;
    private String receiverbank;

}
