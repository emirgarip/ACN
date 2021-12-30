package com.example.acn.demo.repository;

import com.example.acn.demo.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Integer> {

    List<Feature> findAllByIsActive(Boolean active);
}
