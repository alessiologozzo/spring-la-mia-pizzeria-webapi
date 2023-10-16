package org.lessons.java.spring.auth.config;

import org.lessons.java.spring.auth.services.UserService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfiguration {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http)
		throws Exception {
		return http.authorizeHttpRequests(auth -> 
			auth
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
			.requestMatchers("/", "/error", "/forbidden").permitAll()
			.requestMatchers("/pizzas/").hasAnyAuthority("USER", "ADMIN")
			.requestMatchers("/pizzas/**").hasAnyAuthority("ADMIN")
			.requestMatchers("/offers/**").hasAuthority("ADMIN")
			.requestMatchers("/ingredients/**").hasAuthority("ADMIN")
			)
			.formLogin(form -> form.loginPage("/login").permitAll())
			.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"))
			.exceptionHandling(ex -> ex.accessDeniedPage("/forbidden"))
			.build();
	}
	
	@Bean
	UserService userDetailsService() {
		return new UserService();
	}

    @Bean
    PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
    
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
   
      authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());
   
      return authProvider;
    }
}
