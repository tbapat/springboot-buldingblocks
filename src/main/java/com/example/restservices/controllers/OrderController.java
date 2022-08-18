package com.example.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservices.entities.Order;
import com.example.restservices.entities.Users;
import com.example.restservices.exceptions.UserNotFoundException;
import com.example.restservices.repositories.OrderRepository;
import com.example.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value="/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
		Optional<Users> userOptional = userRepository.findById(userid);
		
		if(!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");
		
		
		return userOptional.get().getOrders();
	}
	
	@PostMapping(value="{userid}/orders")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
		Optional<Users> userOptional = userRepository.findById(userid);
		
		if(!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");
		
		Users user = userOptional.get();
		order.setUser(user);
		return orderRepository.save(order);
		
	}
	
	@GetMapping(value="/{userid}/orders/{orderid}")
	public Optional<Order> getOrderById(@PathVariable Long userid, @PathVariable Long orderid) throws UserNotFoundException {
		
		Optional<Users> userOptional = userRepository.findById(userid);
		
		if(!userOptional.isPresent())
			throw new UserNotFoundException("User Not Found");
		
		return orderRepository.findById(orderid);
		
	}
	
}
