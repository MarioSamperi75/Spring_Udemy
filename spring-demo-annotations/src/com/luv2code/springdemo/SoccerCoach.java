package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

@Component//("thatSoccerCoach")
public class SoccerCoach implements Coach {

	@Override
	public String getDailyWorkout() {
		return "Practice 30 minuter freekicks";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return null;
	}

}
