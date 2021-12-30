package com.example.acn.demo.business;

import com.example.acn.demo.dto.FeaturesUsersDto;
import com.example.acn.demo.entity.Feature;
import com.example.acn.demo.entity.User;
import com.example.acn.demo.repository.FeatureRepository;
import com.example.acn.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DemoService {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private UserRepository userRepository;

    public Feature createFeature(Feature feature) {
        return featureRepository.save(feature);
    }

    @Transactional
    public Feature updateFeature(FeaturesUsersDto featuresUsersDto) {
        Feature feature = featureRepository
                .findById(featuresUsersDto.getFeatureId()).orElseThrow(()
                        -> new ResourceNotFoundException("Feature id :: " + featuresUsersDto.getFeatureId()));
        User user = userRepository
                .findById(featuresUsersDto.getUserId()).orElseThrow(()
                        -> new ResourceNotFoundException("User id :: " + featuresUsersDto.getUserId()));

        feature.setIsActive(true);
        if(feature.getUserSet() != null) {
            feature.getUserSet().add(user);
        } else {
            Set<User> userSet = new HashSet<>();
            userSet.add(user);
            feature.setUserSet(userSet);
        }
        return feature;
    }

    public List<Feature> getFeatures() {
        return featureRepository.findAllByIsActive(true);
    }
}