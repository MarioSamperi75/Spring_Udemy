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
	// matching addAccount method just in ONE class : @Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
	// matching all methods starting with add - * as wildcard :@Before("execution(public void add*())")
	// matching all methods starting with add and with any return type: @Before("execution(public * add*())")
	// () no parameters 
	// (*) one argument of any type 
	// (..) 0 or more arguments of any type
	// matching with an argument of a specific type (use qualified name):
			// @Before("execution(public * addAccount(com.luv2code.aopdemo.dao.AccountDAO))")
	// matching with all methods in a specific package:
			// @Before("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	
	
	
	// matching with an argument of a specific type (use qualified name):
	@Before("execution(* add*(com.luv2code.aopdemo.Account))")
		public void beforeAddAccountAdvice() {
		System.out.println("\n=====>> Executing @Before advice on AddAccount()");
	}
  
}
