package com.tag.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tag.domain.Users;
import com.tag.repository.UserRepository;
import com.tag.service.UserService;

@RestController
@RequestMapping(value="api/users")
public class UserController {
	
	private UserRepository userRepository;
	private UserService userService;
	
	@Autowired
	public UserController(UserRepository userRepository,
						  UserService userService) {
		this.userRepository = userRepository;
		this.userService = userService;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Users registerUser(@RequestBody Users user) {
		Users savedUser = userService.save(user);
		return savedUser;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Users> getUsers() {
		return userRepository.findAll();
	}
	

}
