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

	@Before("forDaoPackage()")
		public void beforeAddAccountAdvice() {
		System.out.println("\n=====>> Executing @Before advice on AddAccount()");
	}
	
	//Apply the pointcut declaration to another advice
	@Before("forDaoPackage()")
	public void performApiAnalytics() {
	System.out.println("\n=====>> Performing API Analytics");
}
  
}
