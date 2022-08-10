package com.example.restservices.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservices.entity.UserDetails;

@RestController
public class HelloWorldController {
	
	//@RequestMapping(method=RequestMethod.GET, path="/helloworld")
	@GetMapping("/hello_again")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Ram", "Patil", "Kolhapur");
	}

}
