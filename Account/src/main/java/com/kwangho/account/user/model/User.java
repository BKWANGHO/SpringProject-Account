package com.kwangho.account.user.model;


import com.kwangho.account.account.model.Account;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name="username",unique = true)
    private String username;

    private String password;


    private String name;

    private String address;

    private String job;

    @OneToMany(mappedBy = "user")
    @Column(name = "account")
    private List<Account> account;

    public void setPassword(String password) {
        this.password = password;
    }
}
