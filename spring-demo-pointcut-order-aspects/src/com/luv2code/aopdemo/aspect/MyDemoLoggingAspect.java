package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class MyDemoLoggingAspect {

	//Apply the  combo pointcut declaration to both advices
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
		public void beforeAddAccountAdvice() {
		System.out.println("\n=====>> Executing @Before advice on AddAccount()");
	}
  
}
