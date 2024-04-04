package com.kwangho.account.account;

import lombok.Getter;

@Getter
public class AccountRequestDto {

    private Long userId;
    private String username;
    private String name;
    private String accountPassword;
    private String accountNumber;
    private int totalbalance;
    private String receiverAccount;
    private String bank;
    private String receiverbank;

}
