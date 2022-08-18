package com.example.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.restservices.entities.Users;
import com.example.restservices.exceptions.UserExistsException;
import com.example.restservices.exceptions.UserNameNotFoundException;
import com.example.restservices.exceptions.UserNotFoundException;
import com.example.restservices.services.UserService;


@Validated
@RestController
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<Users> getAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping
	public ResponseEntity<Void> createuser(@Valid @RequestBody Users user, UriComponentsBuilder builder) {
		try {
			userService.createUser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}

	@GetMapping("/{id}")
	public Optional<Users> getUserById(@PathVariable("id") @Min(value = 1) Long id) {
		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

	@PutMapping("/{id}")
	public Users updateUserById(@PathVariable Long id, @RequestBody Users user) {
		try {
			return userService.updateUserById(id, user);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}

	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable Long id) {
		userService.deleteUserById(id);
		return;
	}

	@GetMapping("/byusername/{username}")
	public Users getUserByUsername(@PathVariable String username) throws UserNameNotFoundException {
		Users user = userService.getUserByUserName(username);
		if (user != null)
			return user;
		else
			throw new UserNameNotFoundException("Username: '"+username+"' not found, please enter correct username");

	}

}
