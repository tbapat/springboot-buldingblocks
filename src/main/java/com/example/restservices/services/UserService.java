package com.example.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.restservices.entities.Users;
import com.example.restservices.exceptions.UserExistsException;
import com.example.restservices.exceptions.UserNotFoundException;
import com.example.restservices.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}
	
	public Users createUser(Users user) throws UserExistsException {
		Users optionaluser = userRepository.findByUserName(user.getUserName());
			
		if(optionaluser != null) {
			throw new UserExistsException("User already Exists");
		}
		
		return userRepository.save(user);	
	}
	
	public Optional<Users> getUserById(Long id) throws UserNotFoundException {
		Optional<Users> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("User not Found in User Repository");
		}
		return user;
	}
	
	public Users updateUserById(Long id, Users user) throws UserNotFoundException {
		Optional<Users> optionaluser = userRepository.findById(id);
		
		if(!optionaluser.isPresent()) {
			throw new UserNotFoundException("User not Found in User Repository, provide the correct user id");
		}
			
		user.setId(id);
		return userRepository.save(user);
		
	}
	
	public void deleteUserById(Long id) {
		Optional<Users> optionaluser = userRepository.findById(id);
		
		if(!optionaluser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not Found in User Repository, provide the correct user id");
		}
		userRepository.deleteById(id);
		return;
	}
	
	public Users getUserByUserName(String username) {
		return userRepository.findByUserName(username);
	}
}
