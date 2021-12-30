package com.example.acn.demo.controller;

import com.example.acn.demo.business.DemoService;
import com.example.acn.demo.dto.FeaturesUsersDto;
import com.example.acn.demo.entity.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class DemoController {

    @Autowired
    private DemoService service;

    @PostMapping("/create-feature")
    public ResponseEntity<Object> createFeature(@RequestBody Feature feature) {
        Feature createdFeature = service.createFeature(feature);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdFeature.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/update-feature")
    public ResponseEntity<Object> updateFeature(@RequestBody FeaturesUsersDto featuresUsersDto) {
        service.updateFeature(featuresUsersDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-features")
    public List<Feature> retrieveAllFeatures() {
        return service.getFeatures();
    }
}
