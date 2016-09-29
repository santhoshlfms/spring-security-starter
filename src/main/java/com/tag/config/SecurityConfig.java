package com.tag.config;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Configuration
	protected class AppSecurityConfiguration extends
			WebSecurityConfigurerAdapter {

		@Autowired
		private CustomUserAuthentication customUserAuthentication;

		@Override
		protected void configure(AuthenticationManagerBuilder registry)
				throws Exception {
			registry.userDetailsService(customUserAuthentication);
		}

		private static final int MAX_CONCURRENT_USER_SESSIONS = 1;

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			RequestMatcher csrfRequestMatcher = new RequestMatcher() {
				// Always allow the HTTP GET method
				private Pattern allowedMethods = Pattern.compile("^GET$");
				// Disable CSFR protection on the following urls:
				private AntPathRequestMatcher[] requestMatchers = {
						new AntPathRequestMatcher("/login"),
						new AntPathRequestMatcher("/api/**"),
						new AntPathRequestMatcher("/purchase-program/success"),
						new AntPathRequestMatcher("/purchase-program/failed") };

				@Override
				public boolean matches(HttpServletRequest request) {

					if (allowedMethods.matcher(request.getMethod()).matches()) {
						return false;
					}
					// If the request match one url the CSFR protection will be
					// disabled
					for (AntPathRequestMatcher rm : requestMatchers) {
						if (rm.matches(request)) {
							return false;
						}
					}
					return true;
				} // method matches

			}; // new RequestMatcher

			http.authorizeRequests()
					.antMatchers("/", "/login", "/register/**", "/api/register/**", "/forgot-password/**", "/reset-password/**")
					.permitAll()
					.antMatchers("/admin/**").hasRole("ADMIN")
					.anyRequest()
					.authenticated()
					.and();
			http.formLogin().loginPage("/login")
					.defaultSuccessUrl("/dashboard/")
					.permitAll()
					.and();
			http.sessionManagement()
					.maximumSessions(MAX_CONCURRENT_USER_SESSIONS)
					.expiredUrl("/login?concurrent")
					.maxSessionsPreventsLogin(false)
					.sessionRegistry(sessionRegistry())
					.and();
			http.csrf()
				.requireCsrfProtectionMatcher(csrfRequestMatcher)
				.and();
			http.logout()
				.permitAll();
		}
		
		@Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/static/**");
        }

		@Bean
		public SessionRegistry sessionRegistry() {
			return new SessionRegistryImpl();
		}
	}
}
