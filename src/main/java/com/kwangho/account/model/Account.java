package com.kwangho.account.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.swing.text.StyledEditorKit;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "banking")
public class Account extends BaseEntity implements Persistable<Long> {
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

    @Column(name= "activate")
    private Boolean activate;

@Builder
    public Account(Long id, String username, String password, int balance, String name, String transation, String accountNumber,Boolean activate) {
    super();
    this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.name = name;
        this.transation = transation;
        this.accountNumber = accountNumber;
        this.activate = activate;

    }

    public Account() {
        super();
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
