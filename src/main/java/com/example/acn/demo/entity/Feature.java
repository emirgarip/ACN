package com.example.acn.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Feature entity
 * @author emir
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Feature {

    public Feature(Integer id, String name, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "Name should have min 2 character!")
    @NotBlank
    @NotEmpty
    @NotNull
    private String name;

    @JsonIgnore
    private Boolean isActive = false;


    @ManyToMany
    @JoinTable(
            name = "features_users",
            joinColumns = @JoinColumn(name = "feature_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userSet;

}
