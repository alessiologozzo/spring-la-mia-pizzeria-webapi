package org.lessons.java.spring.auth.services;

import java.util.List;

import org.lessons.java.spring.auth.models.Role;
import org.lessons.java.spring.auth.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	public void save(Role role) {
		roleRepository.save(role);
	}
	
	public void saveAll(List<Role> roles) {
		roleRepository.saveAll(roles);
	}
}
