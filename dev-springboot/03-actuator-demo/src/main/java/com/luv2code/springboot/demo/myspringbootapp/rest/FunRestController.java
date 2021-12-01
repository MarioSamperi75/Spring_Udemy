package com.luv2code.springboot.demo.myspringbootapp.rest;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

	// expose "/" that return "Hello world"
	
	@GetMapping("/")
	public String sayHello() {
		return "Hello  world! Time on server is: " + LocalDateTime.now();
	}
	
	
	//expose a new wndopoint for "workout"
	@GetMapping("/workout")
	public String getDailyWorkout() {
		return "run a hard 5k";
	}
	
	//expose a new wndopoint for "workout"
	@GetMapping("/fortune")
	public String getFortune() {
		return "Today is your lucky day!!";
	}

}
