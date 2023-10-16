package org.lessons.java.spring.auth.services;

import java.util.List;

import org.lessons.java.spring.auth.models.User;
import org.lessons.java.spring.auth.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;

	public void save(User user) {
		userRepository.save(user);
	}
	
	public void saveAll(List<User> users) {
		userRepository.saveAll(users);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userRepository.findByUsername(username);
	}
}
