package com.kwangho.account.history.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kwangho.account.account.model.Account;
import com.kwangho.account.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "histories")
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class History extends BaseEntity {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="account_id")
    private Account accountNumber;

    private int balance;

    private int totalbalance;

    private String transation;






}
