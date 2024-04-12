package com.infy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
