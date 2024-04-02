package com.kwangho.account.dto.request;

import com.kwangho.account.user.User;
import lombok.Getter;

@Getter
public class AccountRequestDto {

    private User username;
    private String name;
    private String password;
    private String accountNumber;
    private int totalbalance;
    private String receiverAccount;
    private String bank;
    private String receiverbank;

}
