package com.kwangho.account.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Persistable;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "banking")
public class Account implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private int balance;

    @Column(name = "name")
    private String name;

    @Column(name = "transation")
    private String transation;

    @Column(name = "accountNumber")
    private String accountNumber;

    @Column(name = "accountdate")
    private LocalDateTime accountdate;

    @Builder
    public Account(String username, String password, int balance, String name, String transation, String accountNumber, LocalDateTime accountdate) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.name = name;
        this.transation = transation;
        this.accountNumber = accountNumber;
        this.accountdate = accountdate;
    }

    public Account() {

    }

    @Override
    public boolean isNew() {


        return false;
    }
}
