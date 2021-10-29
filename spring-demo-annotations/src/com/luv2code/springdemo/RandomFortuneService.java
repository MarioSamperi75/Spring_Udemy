package com.luv2code.springdemo;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {
	
	private String fortunes[] = new String[] { 
			  "Today is your lucky day", 
			  "Today is a normal day", 
			  "Today is a terrible day"
			  };
	
	private Random random = new Random();
	
	@Override
	public String getFortune() {
		int i = random.nextInt(fortunes.length);
		return fortunes[i];
	}
}
