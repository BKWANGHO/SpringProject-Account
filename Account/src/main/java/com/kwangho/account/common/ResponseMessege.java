package com.kwangho.account.common;

import com.kwangho.account.Enum.Messege;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseMessege {
    private String messege;
    private Messege enumMessege;

}
