package com.luv2code.springdemo;

public class TrackCoach implements Coach {
	
	//define a private field for the dependency
	private FortuneService fortuneService;
	
	
	public TrackCoach() {
	
	}
	//define a constructor for the dependency injection

	public TrackCoach(FortuneService theFortuneService) {
		this.fortuneService = theFortuneService;
	}

	@Override
	public String getDailyWorkout() {
		// TODO Auto-generated method stub
		return "Run a hard 5k";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return fortuneService.getFortune();
	}
	
	// add an init method
	
	public void doMyStartupStuff() {
		System.out.println("TrackCoach: inside method doMyStartupStuffs");
	}
	
	// add a destroy method
	public void doMyCleanupStuff() {
		System.out.println("TrackCoach: inside method doMyCleanupStuff");
	}
	
	
	
	
	

}
