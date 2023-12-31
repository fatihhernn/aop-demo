package com.faeren.aopdemo.aspect;

import com.faeren.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(3)
public class MyDemoLoggingAspect {

    @Around("execution(* com.faeren.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n========>>>>>>> Executing @Around on method: " + methodName);

        long begin = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();

        long duration = end - begin;

        System.out.println("======>>>>> Duration : " + duration / 1000.0 + " secondns");

        return result;
    }

    //add a new advice for @AfterThrowing on the accounts method
    @After("execution(* com.faeren.aopdemo.dao.AccountDAO.findAccounts())")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
        //advice'ın uygulandığı metot adının al
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n========>>>>>>> Executing @After (finally) on method: " + method);
    }

    //add a new advice for @AfterThrowing on the accounts method
    @AfterThrowing(
            pointcut = "execution(* com.faeren.aopdemo.dao.AccountDAO.findAccounts(*))",
            throwing = "theExc")
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
        //advice'ın uygulandığı metot adının al
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n========>>>>>>> Executing @AfterThrowing on method: " + method);

        //throw'dan sonra exception'ı yakala
        System.out.println("\n========>>>>>>> Exception is: " + theExc);
    }


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

    //@Before("execution(* com.faeren.add*(..))") bunu yaparak hata aldı enterasan bir biçimde
    @Before("com.faeren.aopdemo.aspect.SampleExpressions.forDaoPackage()")
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
