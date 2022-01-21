package com.example.acn.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * This is for keeping relationship between Feature and User.
 * @author emir
 */
@Getter
@NoArgsConstructor
public class FeaturesUsersDto {
    private Integer featureId;
    private Integer userId;

    public FeaturesUsersDto(Integer featureId, Integer userId) {
        this.featureId = featureId;
        this.userId = userId;
    }
}
