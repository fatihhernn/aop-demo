package com.faeren.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class MyCloudLogAsyncAspect {


    @Before("com.faeren.aopdemo.aspect.SampleExpressions.forDaoPackage()")
    public void logToCloudAsync() {
        System.out.println("\n =====>>>>> @Before advice >>>>> logToCloudAsync()");
    }
}
