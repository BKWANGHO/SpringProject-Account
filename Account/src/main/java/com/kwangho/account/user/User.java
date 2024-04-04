package com.kwangho.account.user;


import com.kwangho.account.account.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name="users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String name;

    private String address;

    private String job;

    @OneToMany(mappedBy = "user")
    @Column(name = "account")
    private List<Account> account;

}
