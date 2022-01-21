package com.example.acn.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

/**
 * User entity
 * @author emir
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    public User(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String username;

    @ManyToMany(mappedBy = "userSet")
    @JsonIgnore
    private Set<Feature> featureSet;
}
