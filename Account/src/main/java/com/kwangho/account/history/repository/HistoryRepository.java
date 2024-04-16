package com.kwangho.account.history.repository;

import com.kwangho.account.account.model.Account;
import com.kwangho.account.history.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History,Long> {

    @Query("SELECT h FROM histories h WHERE h.accountNumber.accountNumber = :accountNumber")
    List<History> findAllByAccountNumber(@Param("accountNumber") String accountNumber);



    Optional<History> findByAccountNumber(Account accountNumber);
}
