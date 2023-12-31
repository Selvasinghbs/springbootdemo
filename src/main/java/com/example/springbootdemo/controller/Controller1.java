package com.example.springbootdemo.controller;

import java.net.URI;

import java.util.Arrays;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.springbootdemo.service.Service1;




@RestController
@RequestMapping("/control1")
public class Controller1 {
	@Autowired
	private Service1 service;
	// GET /users
		// retrieveAllUsers
		@GetMapping("/users")
		public List<User> retrieveAllUsers() {
			return service.findAll();
		}

		// GET /users/{id}
		@GetMapping("/users/{id}")
		public User retrieveUser(@PathVariable int id) {

			User user = service.findOne(id);
			if (user == null)
				throw new UserNotFoundException("id-" + id);

			return user;
		}
	
	
	// input - details of user
		// output - Created & Return the Created URI

		@PostMapping("/users")
		public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {

			User saveduser = service.save(user);

			// CREATED
			// /user/{id} savedUser.getId()

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveduser.getId())
					.toUri();

			return ResponseEntity.created(location).build();

		}

		@DeleteMapping("/users/{id}")
		public void deleteUser(@PathVariable int id) {
			User user = service.deleteById(id);

			if (user == null)
				throw new UserNotFoundException("id-" + id);
		}
		@GetMapping("/friend")
		public List<ControlBean> friend(){
			return Arrays.asList(new ControlBean("Selva","CEO",1),
					new ControlBean("Darunraj","GM",2));
		}
		
}
