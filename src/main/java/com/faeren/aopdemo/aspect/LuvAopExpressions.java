package com.faeren.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LuvAopExpressions {

    @Pointcut(value = "execution(* com.faeren.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {
    }

    ;

}
