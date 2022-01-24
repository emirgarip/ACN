package com.example.acn.demo.repository;

import com.example.acn.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This is for using CRUD operations for USER table.
 * @author emir
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByUsernameAndPassword(String username, String password);
}
