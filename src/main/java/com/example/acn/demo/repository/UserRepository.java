package com.example.acn.demo.repository;

import com.example.acn.demo.entity.Feature;
import com.example.acn.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    public List<Feature> findAllBy
}
