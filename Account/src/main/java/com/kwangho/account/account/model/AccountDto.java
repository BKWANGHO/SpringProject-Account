package com.kwangho.account.account.model;

import com.kwangho.account.history.model.History;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Log4j2
public class AccountDto {

    private Long id;
    private String accountNumber;
    private String receiverAccount;
    private String accountPassword;
    private int totalbalance;
    private String bank;
    private int count;
    private Boolean activate;
    private String user;
    private List<History> histories;
}
