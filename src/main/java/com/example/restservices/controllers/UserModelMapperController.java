package com.example.restservices.controllers;

import java.util.Optional;

import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservices.dtos.UserMmDto;
import com.example.restservices.entities.Users;
import com.example.restservices.exceptions.UserNotFoundException;
import com.example.restservices.services.UserService;

@RestController
@RequestMapping("/modelmapper/users")
public class UserModelMapperController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/{id}")
	public UserMmDto getUserById(@PathVariable("id") @Min(value = 1) Long id) throws UserNotFoundException {
		Optional<Users> useroptional = userService.getUserById(id);
		
		if(!useroptional.isPresent()) {
			throw new UserNotFoundException("User not found");
		}
		Users user = useroptional.get();
		
		UserMmDto userMmDto = modelMapper.map(user, UserMmDto.class);
		return userMmDto;
	}
	
	
	
}
