package com.example.restservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.restservices.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	Users findByUserName(String username);
}
