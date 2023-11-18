package com.codewithprojects.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringsecurityApplication {
	public static void main(String[] args) {

		SpringApplication.run(SpringsecurityApplication.class, args);
	}


//	public void run(String... args){
//		User adminAccount = userRepository.findByRole(Role.ADMIN);
//		if(null == adminAccount){
//			User user = new User();
//
//			user.setEmail("admin@gmail.com");
//			user.setFirstname("admin");
//			user.setSecondname("admin");
//			user.setRole(Role.ADMIN);
//			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
//			userRepository.save(user);
//		}
//	}

}
