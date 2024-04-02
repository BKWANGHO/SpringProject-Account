package com.kwangho.account.dto.response;


import lombok.Getter;

@Getter
public class LoginResponseDto {
    String msg;
    String name;

    public LoginResponseDto(String msg, String name) {
        this.msg = msg;
        this.name = name;
    }
}
