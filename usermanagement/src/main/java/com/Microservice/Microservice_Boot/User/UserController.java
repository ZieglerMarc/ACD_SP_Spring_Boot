package com.Microservice.Microservice_Boot.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
* UserController is a REST controller that handles user-related operations.
* It provides endpoints to retrieve, add, and delete users.
*/
@RestController
@RequestMapping(path = "/api/users")
public class UserController {


    private final UserService userService;

    /*
     * Constructor for UserController.
     * @param userService the service to handle user operations
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
     * This method retrieves all users.
     * @return a ResponseEntity containing a list of User objects
     */
    @GetMapping
    public ResponseEntity<List<User>> getUser() {
        return ResponseEntity.ok(userService.findAll());
    }


    /*
     * This method retrieves a user by ID.
     * @param id the ID of the user to retrieve
     * @return a ResponseEntity containing the User object if found
     * @throws ResourceNotFoundException if the user with the given ID does not exist
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }


    /* 
     * This method adds a new user.
     * @param user the User object to be added
     * @return a ResponseEntity containing the added User object
     * @throws Exception if there is an error while saving the user
     */
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        return ResponseEntity.ok(userService.save(user));
    }


    /*
     * This method deletes a user by ID.
     * @param id the ID of the user to delete
     * @return a ResponseEntity with a message indicating the user has been deleted
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id){
        userService.delete(id);
        return ResponseEntity.ok("User deleted");
    }

}
