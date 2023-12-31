package com.faeren.aopdemo;

import com.faeren.aopdemo.dao.AccountDAO;
import com.faeren.aopdemo.dao.MembershipDAO;
import com.faeren.aopdemo.service.TrafficFortuneService;
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
    public CommandLineRunner commandLineRunner(AccountDAO theAccountDao, MembershipDAO theMembershipDAO, TrafficFortuneService trafficFortuneService) {
        return runner -> {
            //demoTheBeforeAdvice(theAccountDao);
            //demoTheBeforeAdviceForMembership(theMembershipDAO);
            //demoTheAfterReturningAdvice(theAccountDao);
            //demoTheAfterThrowingAdvice(theAccountDao);
            //demoTheAfterAdvice(theAccountDao);
            demoTheAroundAdvice(trafficFortuneService);
        };
    }

    private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {
        System.out.println("\nMain Program  : demoTheAroundAdvice");
        System.out.println("Calling getFortune()");
        String data = trafficFortuneService.getFortune();
        System.out.println("My fortune is  : " + data);
        System.out.println("Finished");
    }

    private void demoTheAfterAdvice(AccountDAO theAccountDao) {
        List<Account> accounts = null;
        try {
            boolean tripWire = false;
            accounts = theAccountDao.findAccounts(tripWire);
        } catch (Exception e) {
            System.out.println("\n\nMain Program  : caught exception " + e);
        }

        System.out.println("\n\nMain Program : demoTheAfterThrowingAdvice");
        System.out.println("Result data : " + accounts);
        System.out.println("----------");
        System.out.println("\n");
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
