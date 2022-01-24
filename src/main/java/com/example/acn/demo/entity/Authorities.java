package com.example.acn.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Authorities entity
 * @author emir
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Authorities {

    public Authorities(Integer id, Users username, String authority) {
        this.id = id;
        this.username = username;
        this.authority = authority;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users username;

    private String authority;
}
