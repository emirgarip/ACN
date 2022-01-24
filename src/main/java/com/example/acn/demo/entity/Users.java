package com.example.acn.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * User entity
 * @author emir
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Users {

    public Users(Integer id, String username, String password, Boolean enabled) {
        this.id = id;
        this.enabled = enabled;
        this.password = password;
        this.username = username;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String username;

    private String password;

    private Boolean enabled;

    @ManyToMany(mappedBy = "userSet")
    @JsonIgnore
    private Set<Feature> featureSet;

    @OneToMany(mappedBy = "username", fetch = FetchType.EAGER)
    private Set<Authorities> authoritiesSet;

}
