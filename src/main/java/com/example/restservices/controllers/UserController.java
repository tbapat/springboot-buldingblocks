package com.example.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservices.entities.Users;
import com.example.restservices.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<Users> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping("/users")
	public Users createuser(@RequestBody Users user) {
		return userService.createUser(user);
	}
	
	@GetMapping("/users/{id}")
	public Optional<Users> getUserById(@PathVariable("id") Long id) {
		return userService.getUserById(id);		
	}
	
	@PutMapping("/users/{id}")
	public Users updateUserById(@PathVariable Long id, @RequestBody Users user) {
		return userService.updateUserById(id, user);		
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable Long id) {
		userService.deleteUserById(id);
		return;
	}

	@GetMapping("users/byusername/{username}")
	public Users getUserByUsername(@PathVariable String username) {
		return userService.getUserByUserName(username);
	}
}
