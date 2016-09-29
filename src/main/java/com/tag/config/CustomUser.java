package com.tag.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.tag.domain.Users;

public class CustomUser extends org.springframework.security.core.userdetails.User {
	
	private String displayName;
	private Long id;
	
	public CustomUser(Users user) {
		super(user.getUsername(), user.getPassword(), true, true, true, true, buildAuthorities(user));
		    this.displayName = user.getUsername();
	        this.id = user.getId();
	}
	
	private static Collection<? extends GrantedAuthority> buildAuthorities(Users user) {
	        Collection<GrantedAuthority> authorities = new ArrayList<>();
	        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
	        authorities.add(authority);
	        return authorities;
	    }
	
	public String getDisplayName() {
		return displayName;
	}

	public Long getId() {
		return id;
	}
}
