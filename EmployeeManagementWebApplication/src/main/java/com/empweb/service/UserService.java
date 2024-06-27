package com.empweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.empweb.entity.User;
import com.empweb.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
	BCryptPasswordEncoder bcryptEncoder;

//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    
//  @Bean
//  public PasswordEncoder passwordEncoder() {
//      return new BCryptPasswordEncoder();
//  }


    public User createUser(User user) {
    	user.setPassword(bcryptEncoder.encode(user.getPassword()));
    	return userRepository.save(user);
    }
}
