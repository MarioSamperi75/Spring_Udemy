package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RandomFortuneApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// load the spring configuration file
		ClassPathXmlApplicationContext context =
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// retrieve bean from spring container
		SoccerCoach theCoach = context.getBean("soccerCoach", SoccerCoach.class);
		
		// call methods on the bean
		for(int i=0; i < 10 ;i++) {
			
			System.out.println(theCoach.getDailyWorkout());
			System.out.println(theCoach.getDailyFortune());
		}
	
		// close the context
		context.close();

	}

}
