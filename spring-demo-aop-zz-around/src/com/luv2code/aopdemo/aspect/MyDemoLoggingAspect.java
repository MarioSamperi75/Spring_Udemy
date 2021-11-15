package com.luv2code.aopdemo.aspect;

import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AbstractAdvisingBeanPostProcessor; 
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;


@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroungGetFortune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		//print out the method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString(); 	 	
		System.out.println("\n======>Executing @Around on method: " + method);
		
		// get begin timestamp
		long begin = System.currentTimeMillis();
		
		// now, let's execute the method
		Object result = theProceedingJoinPoint.proceed();
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		// compute duration and display it
		long duration = end - begin;
		System.out.println("\n======> Duration " +duration/1000.0 + " seconds");
		
		return result;
		
	}
	
	// @After will run for success and for failure!! 
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountAdvice(JoinPoint theJoinPoint) {
		
		// print out witch method we are advising on 
				String method = theJoinPoint.getSignature().toShortString(); 	 	
				System.out.println("\n======>Executing @After (Finally) on method: " + method);
		
	}
	
	
	@AfterThrowing(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "theExc"
			)
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		
		// print out witch method we are advising on 
		String method = theJoinPoint.getSignature().toShortString(); 	 	
		System.out.println("\n======>Executing @AfterThrowing on method: " + method);
		
		// log the exception
		System.out.println("\n======>the exception is: " + theExc); 
		
	}
	
	//add a new advice for @AfterReturning on the findAccounts method
	@AfterReturning(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", 
			returning="result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		
		// print out witch method we are advising on
		String method = theJoinPoint.getSignature().toShortString(); 	 	
		System.out.println("\n======>Executing @AfterReturning on method: " + method);
		
		// print out the results of the method call
		System.out.println("\n======>result is: " + result);
		
		// Let's post-process the data and let's modify it!!
		
		// convert the account names to uppercase
		convertAccountNamesToUpperCase(result);
		
		System.out.println("\n======>result is: " + result);
	}

	private void convertAccountNamesToUpperCase(List<Account> result) {
		// loop through accounts
		for(Account tempAccount : result) {
			
			// get uppercase version of name	 
			String theUpperName = tempAccount.getName().toUpperCase();
			
			// update the name on the account
			tempAccount.setName(theUpperName);			
		}
		
	}

	//Apply the  combo pointcut declaration to both advices
	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
		public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		System.out.println("\n=====>> Executing @Before advice on AddAccount()");
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		
		System.out.println("Method: " + methodSig);
		
		// display the method arguments
		Object[] args =  theJoinPoint.getArgs();
		
		// loop thru args
		for(Object tempArg: args) {
			System.out.println(tempArg);
			
			if (tempArg instanceof Account) {
				//downcast and print account specific stuff
				Account theAccount = (Account) tempArg;
				
				System.out.println("Account name: " + theAccount.getName());
				System.out.println("Account level: " + theAccount.getLevel());
			}
			
			
		}
	}
  
}
