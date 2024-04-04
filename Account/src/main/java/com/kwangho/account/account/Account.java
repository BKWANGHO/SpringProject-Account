package com.kwangho.account.account;

import com.kwangho.account.common.BaseEntity;
import com.kwangho.account.history.History;
import com.kwangho.account.user.User;
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

    @Column(name = "account_password")
    private String accountPassword;

    private String username;


    private int totalBalance;

    @Column(name = "account_number",unique = true)
    private String accountNumber;

    private String bank;

    private Boolean activate;

    private int count;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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
