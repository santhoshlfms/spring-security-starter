package com.tag.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.tag.domain.Users;


public final class SecurityUtils {
	
	  public static Long currentUserId() {
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        CustomUser customUser = (CustomUser) principal;
	        return customUser.getId();
	    }

	    public static void loginUser(Users user) {
	        CustomUser authenticationPrincipal = new CustomUser(user);
	        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationPrincipal, user.getPassword(), authenticationPrincipal.getAuthorities());
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	    }

}
