package com.tag.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tag.domain.Users;

public interface UserRepository extends JpaRepository<Users, Long>{
	
	Users findByUsername(String username);
	List<Users> findAll();

}
