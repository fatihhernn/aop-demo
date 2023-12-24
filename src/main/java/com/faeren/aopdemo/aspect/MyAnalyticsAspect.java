package com.faeren.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyAnalyticsAspect {

    @Before("com.faeren.aopdemo.aspect.LuvAopExpressions.forDaoPackage()")
    public void forAnalyticsAdvice() {
        System.out.println("\n =====>>>>> @Before advice >>>>> forAnalyticsAdvice()");
    }

}
