package com.luv2code.springdemo;

public class SoccerCoach  implements Coach{

	//define a private field for the dependency
	private FortuneService fortuneService;
	
	//define a constructor for the dependency injection

	public SoccerCoach(FortuneService theFortuneService) {
		this.fortuneService = theFortuneService;
	}
	
	
	@Override
	public String getDailyWorkout() {
		return "Try  50 free kicks";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return fortuneService.getFortune();
	}

}
