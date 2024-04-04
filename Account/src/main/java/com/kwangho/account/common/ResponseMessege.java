package com.kwangho.account.common;

import com.kwangho.account.Enum.Messege;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessege {
    private String messege;
    private Messege enumMessege;

}
