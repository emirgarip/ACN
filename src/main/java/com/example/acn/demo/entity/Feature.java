package com.example.acn.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Feature {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "Name should have min 2 character!")
    private String name;


    private Boolean isActive = false;


    @ManyToMany
    @JoinTable(
            name = "features_users",
            joinColumns = @JoinColumn(name = "feature_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userSet;
}
