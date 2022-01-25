package com.example.acn.demo.controller;

import com.example.acn.demo.business.FeatureService;
import com.example.acn.demo.dto.FeaturesUsersDto;
import com.example.acn.demo.entity.Feature;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Feature Rest Controller
 * @author emir
 */
@RestController
@RequestMapping("/feature")
public class FeatureController {

    private FeatureService featureService;

    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    /**
     * Creating feature method. It's designed Post mapping.
     * Return type is a ResponseEntity. It's a best practice.
     * If there is no exception, return status code is 201, but if there is an exception return status code is 500.
     * @param feature
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Object> createFeature(@RequestBody Feature feature) {
        Feature createdFeature = featureService.createFeature(feature);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdFeature.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * Updating features. It's designed Put mapping.
     * If there is no exception, return status code is 200, but if there is an exception return status code is 404.
     * @param featuresUsersDto
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Object> updateFeature(@RequestBody FeaturesUsersDto featuresUsersDto) {
        featureService.updateFeature(featuresUsersDto);
        return ResponseEntity.ok().build();
    }

    /**
     * Retrieving all active features for every users.
     * @return
     */
    @GetMapping("/get")
    public List<Feature> retrieveAllFeatures() {
        return featureService.getFeatures();
    }
}
