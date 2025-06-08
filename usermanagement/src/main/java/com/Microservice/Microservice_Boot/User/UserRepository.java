package com.Microservice.Microservice_Boot.User;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * UserRepository is an interface that extends JpaRepository to provide CRUD operations for User entities.
 * It is used to interact with the database.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
