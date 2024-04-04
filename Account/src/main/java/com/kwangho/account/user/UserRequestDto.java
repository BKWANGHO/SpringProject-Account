package com.kwangho.account.user;

import lombok.Getter;

@Getter
public class UserRequestDto {
    private String username;
    private String password;
    private String name;
    private String address;
    private String job;

}
