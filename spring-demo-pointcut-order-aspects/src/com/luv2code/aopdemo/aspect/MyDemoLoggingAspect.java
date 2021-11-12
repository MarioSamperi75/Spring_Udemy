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
	
	// create pointcut for getter methods
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
	private void getter() {}
	
	// create pointcut for setter methods
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
	private void setter() {}
	
	
	// create pointcut include package ...exclude  getter/setter
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	private void forDaoPackageNoGetterSetter() {};
	
	
	
	//Apply the  combo pointcut declaration to both advices
	@Before("forDaoPackageNoGetterSetter()")
		public void beforeAddAccountAdvice() {
		System.out.println("\n=====>> Executing @Before advice on AddAccount()");
	}
	
	@Before("forDaoPackageNoGetterSetter()")
	public void performApiAnalytics() {
	System.out.println("\n=====>> Performing API Analytics");
	}
	
	@Before("forDaoPackageNoGetterSetter()")
	public void logToCloudAsync() {
	System.out.println("\n=====>> Loggin to Cloud in Async Fashion");
	}
  
}
