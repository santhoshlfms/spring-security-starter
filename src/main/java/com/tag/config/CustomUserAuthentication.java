package com.tag.config;

import java.io.Serializable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.tag.domain.Users;
import com.tag.repository.UserRepository;

@SuppressWarnings("serial")
@Component
public class CustomUserAuthentication implements UserDetailsService, Serializable {

	private UserRepository userRepository;
	
	public CustomUserAuthentication(UserRepository userRepository) {
		this.userRepository =  userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		Users user = userRepository.findByUsername(username);
		 if(user == null) {
	            throw new UsernameNotFoundException("UserName " + username + " not found");
	        }
		 System.out.println("Is User Null  :" + user);
		return new CustomUser(user);
	}
}
