package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

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
import com.luv2code.aopdemo.AroundWithLoggerDemoApp;


@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
	
	private static Logger myLogger = Logger.getLogger(AroundWithLoggerDemoApp.class.getName());
	
	@Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroungGetFortune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		//print out the method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString(); 	 	
		myLogger.info("\n======>Executing @Around on method: " + method);
		
		// get begin timestamp
		long begin = System.currentTimeMillis();
		
		// now, let's execute the method
		Object result=null;
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			// log the exception
			myLogger.warning(e.getMessage());
			
			
			// rethrow exception	
			throw e;
			
			// give user a custom message
			// result = "Major accident! But don't worries... " + 
			// "we handled it in the around advice!";
		}
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		// compute duration and display it
		long duration = end - begin;
		myLogger.info("\n======> Duration " +duration/1000.0 + " seconds");
		
		return result;
		
	}
	
	// @After will run for success and for failure!! 
	@After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountAdvice(JoinPoint theJoinPoint) {
		
		// print out witch method we are advising on 
				String method = theJoinPoint.getSignature().toShortString(); 	 	
				myLogger.info("\n======>Executing @After (Finally) on method: " + method);
		
	}
	
	
	@AfterThrowing(
			pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
			throwing = "theExc"
			)
	public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc) {
		
		// print out witch method we are advising on 
		String method = theJoinPoint.getSignature().toShortString(); 	 	
		myLogger.info("\n======>Executing @AfterThrowing on method: " + method);
		
		// log the exception
		myLogger.info("\n======>the exception is: " + theExc); 
		
	}
	
	//add a new advice for @AfterReturning on the findAccounts method
	@AfterReturning(
			pointcut="execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))", 
			returning="result")
	public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
		
		// print out witch method we are advising on
		String method = theJoinPoint.getSignature().toShortString(); 	 	
		myLogger.info("\n======>Executing @AfterReturning on method: " + method);
		
		// print out the results of the method call
		myLogger.info("\n======>result is: " + result);
		
		// Let's post-process the data and let's modify it!!
		
		// convert the account names to uppercase
		convertAccountNamesToUpperCase(result);
		
		myLogger.info("\n======>result is: " + result);
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
		myLogger.info("\n=====>> Executing @Before advice on AddAccount()");
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		
		myLogger.info("Method: " + methodSig);
		
		// display the method arguments
		Object[] args =  theJoinPoint.getArgs();
		
		// loop thru args
		for(Object tempArg: args) {
			myLogger.info(tempArg.toString());
			
			if (tempArg instanceof Account) {
				//downcast and print account specific stuff
				Account theAccount = (Account) tempArg;
				
				myLogger.info("Account name: " + theAccount.getName());
				myLogger.info("Account level: " + theAccount.getLevel());
			}
			
			
		}
	}
  
}
