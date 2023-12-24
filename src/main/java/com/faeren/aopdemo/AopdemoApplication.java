package com.faeren.aopdemo;

import com.faeren.aopdemo.dao.AccountDAO;
import com.faeren.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopdemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO theAccountDao, MembershipDAO theMembershipDAO) {
        return runner -> {
            //demoTheBeforeAdvice(theAccountDao);
            //demoTheBeforeAdviceForMembership(theMembershipDAO);
            //demoTheAfterReturningAdvice(theAccountDao);
            demoTheAfterThrowingAdvice(theAccountDao);
        };
    }

    private void demoTheAfterThrowingAdvice(AccountDAO theAccountDao) {
        List<Account> accounts = null;
        try {
            boolean tripWire = true;
            accounts = theAccountDao.findAccounts(tripWire);
        } catch (Exception e) {
            System.out.println("\n\nMain Program  : caught exception " + e);
        }

        System.out.println("\n\nMain Program : demoTheAfterThrowingAdvice");
        System.out.println(accounts);
        System.out.println("----------");
        System.out.println("\n");

    }

    private void demoTheAfterReturningAdvice(AccountDAO theAccountDao) {
        List<Account> accounts = theAccountDao.findAccounts();
        /*
        System.out.println("\n\nMain Program : demoTheAfterReturningAdvice");
        System.out.println(accounts);
        System.out.println("----------");
        System.out.println("\n");

         */

    }

    private void demoTheBeforeAdviceForMembership(MembershipDAO theMembershipDAO) {
        theMembershipDAO.addAccount();
        theMembershipDAO.goToSleep();
    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDao) {
        Account account = new Account();
        account.setName("fatih");
        account.setLevel("business");
        theAccountDao.addAccount(account, false);
        theAccountDao.doWork();
    }

}
