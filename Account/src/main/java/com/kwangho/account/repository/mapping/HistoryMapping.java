package com.kwangho.account.repository.mapping;

import java.time.LocalDateTime;

public interface HistoryMapping {
    int getBalance();
    int getTotalbalance();

    String getTransation();

    LocalDateTime getCreateDate();
}
