package com.Microservice.Microservice_Boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@SpringBootApplication
@RestController
public class MicroserviceBootApplication {
	@RequestMapping("/")
	public String home() {
		return "Hello Docker World";
	}

	@GetMapping(path = "/user/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		System.out.println(id);
		return new ResponseEntity.ok();
	}

	@GetMapping(path = "/users")
	public ResponseEntity<Collection<User>> getUser() {
		System.out.println("Hello Docker World");
		return new ResponseEntity.ok();
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
		return new ResponseEntity.ok();
	}

	@PutMapping(path = "/add")
	public ResponseEntity<?> addUser(@RequestBody User user){
		System.out.println(user);
		return new ResponseEntity.ok();
	}

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceBootApplication.class, args);
	}

}
