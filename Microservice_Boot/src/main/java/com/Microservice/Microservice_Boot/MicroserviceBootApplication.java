package com.Microservice.Microservice_Boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class MicroserviceBootApplication {
	@RequestMapping("/")
	public String home() {
		return "Hello Docker World1";
	}

	@GetMapping(path = "/user/{id}")
	public ResponseEntity<String> getUser(@PathVariable("id") int id) {
		System.out.println(id);
		return ResponseEntity.ok("Hello Docker World");
	}

	@GetMapping(path = "/users")
	public ResponseEntity<?> getUser() {
		System.out.println("Hello Docker World");
		return ResponseEntity.ok("Hello Docker World");
	}

	@DeleteMapping(path = "/deleteById/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
		return ResponseEntity.ok("Hello Docker World");
	}

	@PutMapping(path = "/add")
	public ResponseEntity<?> addUser(@RequestBody User user){
		System.out.println(user);
		return ResponseEntity.ok("Hello Docker World");
	}

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceBootApplication.class, args);
	}

	private class User {
		String name;
	}
}
