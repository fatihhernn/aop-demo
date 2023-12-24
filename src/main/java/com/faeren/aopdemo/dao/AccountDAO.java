package com.faeren.aopdemo.dao;

import com.faeren.aopdemo.Account;

import java.util.List;

public interface AccountDAO {

    void addAccount(Account account, boolean flag);

    boolean doWork();

    List<Account> findAccounts();
}
