package com.tag.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tag.config.SecurityUtils;

@Aspect
@Component
public class UserServiceAspect {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceAspect.class);
    
    @Before("execution(* com.tag.service.*.save(..))")
    public void beforeUserCreation() {
    	System.out.println("User" + SecurityUtils.currentUserId());
    }
    
    
    
    

    
    protected void saveEntity() {
    }

}
