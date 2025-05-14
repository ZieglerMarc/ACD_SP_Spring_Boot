package com.Microservice.Microservice_Boot.User;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    /*TODO gedanken über das Ausleihen machen. wenn in User, dann neuer Table
    * wenn in neuem Service dann Check auf Relation
    * */
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public boolean update(User user) {
        if(userRepository.existsById(user.getId())){
            userRepository.save(user);
            return true;
        }
        return false;
    }

}
