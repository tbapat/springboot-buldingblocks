package com.example.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservices.entities.Users;
import com.example.restservices.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}
	
	public Users createUser(Users user) {
		return userRepository.save(user);
		
	}
	
	public Optional<Users> getUserById(Long id) {
		Optional<Users> user = userRepository.findById(id);
		return user;
	}
	
	public Users updateUserById(Long id, Users user) {
		user.setId(id);
		return userRepository.save(user);
		
	}
	
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
		return;
	}
	
	public Users getUserByUserName(String username) {
		return userRepository.findByUserName(username);
	}
}
