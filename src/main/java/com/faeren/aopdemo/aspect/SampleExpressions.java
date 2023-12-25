package com.faeren.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class SampleExpressions {

    @Pointcut(value = "execution(* com.faeren.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {
    }
}
