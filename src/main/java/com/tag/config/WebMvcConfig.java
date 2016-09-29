package com.tag.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;



@Configuration
public class WebMvcConfig {
	
	@Bean
    public WebConfig webConfigAdapter() {
        return new WebConfig();
    }
	
	protected class WebConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			 registry.addViewController("/home").setViewName("authentication/home");
		     registry.addViewController("/").setViewName("authentication/login");
		     registry.addViewController("/hello").setViewName("authentication/hello");
		     registry.addViewController("/login").setViewName("authentication/login");
		     //registry.addViewController("/dashboard").setViewName("dashboard");
	    }
	}
}
