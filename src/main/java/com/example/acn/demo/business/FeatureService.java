package com.example.acn.demo.business;

import com.example.acn.demo.dto.FeaturesUsersDto;
import com.example.acn.demo.entity.Feature;
import com.example.acn.demo.entity.Users;
import com.example.acn.demo.repository.FeatureRepository;
import com.example.acn.demo.repository.UserRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Feature Service is a business layer in the application.
 * @author emir
 */
@Service
public class FeatureService {

    private FeatureRepository featureRepository;
    private UserRepository userRepository;

    public FeatureService(FeatureRepository featureRepository, UserRepository userRepository) {
        this.featureRepository = featureRepository;
        this.userRepository = userRepository;
    }

    /**
     * For calling the repository method.
     * @param feature
     * @return
     */
    public Feature createFeature(Feature feature) {
        return featureRepository.save(feature);
    }

    /**
     * There are some logic operations. Before updating, we should check the data from tables.
     * @param featuresUsersDto
     * @return
     */
    @Transactional
    public Feature updateFeature(FeaturesUsersDto featuresUsersDto) {
        Feature feature = featureRepository
                .findById(featuresUsersDto.getFeatureId()).orElseThrow(()
                        -> new ResourceNotFoundException("Feature id :: " + featuresUsersDto.getFeatureId()));
        Users users = userRepository
                .findById(featuresUsersDto.getUserId()).orElseThrow(()
                        -> new ResourceNotFoundException("User id :: " + featuresUsersDto.getUserId()));

        feature.setIsActive(true);
        if(feature.getUserSet() != null) {
            feature.getUserSet().add(users);
        } else {
            Set<Users> usersSet = new HashSet<>();
            usersSet.add(users);
            feature.setUserSet(usersSet);
        }
        return feature;
    }

    /**
     * retrieving all active features. Return type is list.
     * @return
     */
    public List<Feature> getFeatures() {
        return featureRepository.findAllByIsActive(true);
    }
}