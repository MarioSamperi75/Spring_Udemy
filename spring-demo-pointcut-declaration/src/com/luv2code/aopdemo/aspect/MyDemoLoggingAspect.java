package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	// Create pointcut declaration
	// we will reuse just the name of the method!
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
	private void forDaoPackage() {}

	// Apply pointcut declaration to advice
	@Before("forDaoPackage()")
		public void beforeAddAccountAdvice() {
		System.out.println("\n=====>> Executing @Before advice on AddAccount()");
	}
	
	// all methods are in the package so all methods will print the log!
  
}
