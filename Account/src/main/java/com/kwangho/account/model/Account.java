package com.kwangho.account.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import java.util.List;

@Entity
@Getter
@Table(name = "accounts")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account extends BaseEntity implements Persistable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "username")
    private User username;

    private String password;

    private int totalBalance;

    private String name;

    @Column(name = "accountNumber",unique = true)
    private String accountNumber;

    private String bank;

    private Boolean activate;

    private int count;

    @OneToMany(mappedBy = "accountNumber")
    private List<History> histories;

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public void setTotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
