package com.example.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservices.dtos.UserMsDto;
import com.example.restservices.entities.Users;
import com.example.restservices.mappers.UserMapper;
import com.example.restservices.repositories.UserRepository;

@RestController
@RequestMapping("/mapstruct/users")
public class UsersMapStructController {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;
	
	@GetMapping
	public List<UserMsDto> getAllUserDtos() {
		return userMapper.usersToUserMsDtos(userRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public UserMsDto getUserById(@PathVariable Long id) {
		Optional<Users> userOptional = userRepository.findById(id);
		Users user = userOptional.get();
				
		return userMapper.usertoUserMsDto(user);
		
	}
}
