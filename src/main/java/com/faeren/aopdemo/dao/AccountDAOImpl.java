package com.faeren.aopdemo.dao;

import com.faeren.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {


    @Override
    public void addAccount(Account account, boolean flag) {
        System.out.println(getClass() + " : DOING MY DB WORK : ADDING AN ACCOUNT");

    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + " : doWork()");
        return false;
    }

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {
        // for academic purposes..  simulate an exception
        if (tripWire) {
            throw new RuntimeException("No soup for you!!!!");
        }
        return List.of(
                new Account("fatih", "12345"),
                new Account("furkan", "1234567"));
    }
}
