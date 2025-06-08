package com.Microservice.Microservice_Boot.User;

import org.springframework.stereotype.Service;

import java.util.List;

/* 
 * UserService is a service class that provides methods to manage users.
 * It interacts with the UserRepository to perform CRUD operations on User entities.
 */
@Service
public class UserService {

    /*
     * UserRepository is an interface that extends JpaRepository to provide CRUD operations for User entities.
     * It is used to interact with the database.
     */
    private UserRepository userRepository;

    /*
     * Constructor for UserService.
     * @param userRepository the repository to handle user operations
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * This method saves a user to the database.
     * @param user the User object to be saved
     * @return the saved User object
     * @throws Exception if there is an error while saving the user
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /*
     * This method retrieves all users from the database.
     * @return a list of User objects
     * @throws Exception if there is an error while retrieving users
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /* 
     * This method retrieves a user by ID.
     * @param id the ID of the user to retrieve
     * @return the User object if found, or null if not found
     * @throws Exception if there is an error while retrieving the user
     */
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /*
     * This method deletes a user by ID.
     * @param id the ID of the user to delete
     * @throws Exception if there is an error while deleting the user
     */
    public void delete(long id) {
        userRepository.deleteById(id);
    }


}
