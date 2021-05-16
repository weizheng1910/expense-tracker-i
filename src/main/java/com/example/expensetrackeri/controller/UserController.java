package com.example.expensetrackeri.controller;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetrackeri.model.User;
import com.example.expensetrackeri.repository.RoleRepository;
import com.example.expensetrackeri.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {

	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    private RoleRepository roleRepository;
	
    @Autowired 
    private UserRepository userRepository;

   
    @PostMapping("/newAccount")
    public void registration(@RequestBody User user) {
    	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
 
      
    }

    
}