package com.faeren.aopdemo.aspect;

import com.faeren.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(3)
public class MyDemoLoggingAspect {

    //Add a new advice for @AfterReturning on the find accounts method
    @AfterReturning(
            pointcut = "execution(* com.faeren.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

        //advice'ın uygulandığı metot adının al
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n========>>>>>>> Executing @AfterReturning on method: " + method);

        //After'dan sonra result'ı yakala
        System.out.println("\n========>>>>>>> Result is: " + result);

        //veriler işlenmeden önce onları modify edebiliriz
        convertAccountNamesToUpperCase(result);

        System.out.println(result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        result.forEach(tempAccount -> {
            String upperName = tempAccount.getName().toUpperCase();
            tempAccount.setName(upperName);
        });
    }

    //@Before("execution(* com.faeren..add*(..))") bunu yaparak hata aldı enterasan bir biçimde
    @Before("com.faeren.aopdemo.aspect.LuvAopExpressions.forDaoPackage()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
        System.out.println("\n =====>>>>> @Before advice çalıştı on addAccountMethod()");

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        System.out.println("Method Signature : " + signature);

        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            System.out.println("parametre == " + arg);

            if (arg instanceof Account) {
                Account account = (Account) arg;

                System.out.println("Account Name == " + account.getName());

            }
        }

    }
}
