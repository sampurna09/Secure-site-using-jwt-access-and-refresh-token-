package com.jwt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jwt.domain.Role;
import com.jwt.domain.User;
import com.jwt.service.UserService;

@SpringBootApplication
public class JwtDemoApplication implements CommandLineRunner {
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(JwtDemoApplication.class, args);
	}
	
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Override
	public void run(String... args) throws Exception {
		
		userService.saveRole(new Role(null,"ROLE_USER"));
		userService.saveRole(new Role(null,"ROLE_MANAGER"));
		userService.saveRole(new Role(null,"ROLE_ADMIN"));
		userService.saveRole(new Role(null,"ROLE_SUPERADMIN"));
		
		userService.saveUser(new User(null, "john j", "john1", "1234", new ArrayList<>()));
		userService.saveUser(new User(null, "john k", "john2", "1234", new ArrayList<>()));
		userService.saveUser(new User(null, "john l", "john3", "1234", new ArrayList<>()));
		userService.saveUser(new User(null, "john m", "john4", "1234", new ArrayList<>()));
		
		
		userService.addRoleToUser("john1", "ROLE_USER");
		userService.addRoleToUser("john1", "ROLE_MANAGER");
		userService.addRoleToUser("john2", "ROLE_ADMIN");
		userService.addRoleToUser("john2", "ROLE_SUPER_ADMIN");
		userService.addRoleToUser("john3", "ROLE_USER");
		userService.addRoleToUser("john4", "ROLE_ADMIN");
		userService.addRoleToUser("john4", "ROLE_USER");
		
		
	}

}
