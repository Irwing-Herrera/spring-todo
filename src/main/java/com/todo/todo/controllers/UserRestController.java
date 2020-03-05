package com.todo.todo.controllers;

import com.todo.todo.entity.Usuario;
import com.todo.todo.services.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
	private IUserService userService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
 
    @PostMapping("/registrar")
	public ResponseEntity<Void> addUser(@RequestBody Usuario user) {
		if(userService.findUser(user)==null) {
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			userService.save(user);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	
	}
}