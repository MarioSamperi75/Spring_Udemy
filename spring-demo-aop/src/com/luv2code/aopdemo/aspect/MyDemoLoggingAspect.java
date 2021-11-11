package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	//this is where we add all of our related advices for logging
	
	//let's start with an @Before advice
	
	// Pointcut syntax: execution( verifier? returnType class.? methodName(params)  throws?) 
	// macthing addAccount method in any class : @Before("execution(public void addAccount())")
	
	//matching addAccount method just in ONE class
	@Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
		public void beforeAddAccountAdvice() {
		System.out.println("\n=====>> Executing @Before advice on AddAccount()");
	}

}
