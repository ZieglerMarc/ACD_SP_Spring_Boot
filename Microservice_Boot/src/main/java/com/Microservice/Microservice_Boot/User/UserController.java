package com.Microservice.Microservice_Boot.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<User>> getUser() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
        userService.delete(id);
        return ResponseEntity.ok("User deleted");
    }

    @PutMapping(path = "/")
    public ResponseEntity<User> addUser(@RequestBody User user){

        System.out.println(user);
        return ResponseEntity.ok(userService.save(user));
    }
}
