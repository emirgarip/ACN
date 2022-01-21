package com.example.acn.demo.controller;

import com.example.acn.demo.business.FeatureService;
import com.example.acn.demo.dto.FeaturesUsersDto;
import com.example.acn.demo.entity.Feature;
import com.example.acn.demo.entity.User;
import com.example.acn.demo.repository.FeatureRepository;
import com.example.acn.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;

/**
 * FeatureController test class
 * @author emir
 */
@RunWith(MockitoJUnitRunner.class)
public class FeatureControllerTest {

    @InjectMocks
    private FeatureController featureController;

    @Mock
    private FeatureService featureService;

    Feature failedFeature;
    Feature feature;
    User user;
    FeaturesUsersDto failedDto;
    FeaturesUsersDto dto;
    FeatureRepository featureRepository;
    UserRepository userRepository;

    /**
     * Before tests run, create some necessery objects for the tests.
     */
    @BeforeEach
    public void init() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        featureRepository = Mockito.mock(FeatureRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        featureService = new FeatureService(featureRepository, userRepository);
        featureController = new FeatureController(featureService);

        failedFeature = new Feature(2,"a",false);
        feature = new Feature(1,"feature1",false);
        user = new User(1001,"user1");

        failedDto = new FeaturesUsersDto();
        dto = new FeaturesUsersDto(1,1001);

        when(featureRepository.save(failedFeature)).thenThrow(RuntimeException.class);
        when(featureRepository.save(feature)).thenReturn(feature);
        when(featureRepository.findById(dto.getFeatureId())).thenReturn(Optional.of(feature));
        when(userRepository.findById(dto.getUserId())).thenReturn(Optional.of(user));
    }

    /**
     * Testing create feature with invalid values
     */
    @Test
    public void createFeature_InvalidRequest_ShouldThrowException () {
        Throwable thrown = catchThrowable(() -> {
            featureController.createFeature(failedFeature);
        });
        assertThat(thrown).isInstanceOf(RuntimeException.class);
    }

    /**
     * Testing create feature with valid values
     */
    @Test
    public void createFeature_ValidRequest_ShouldNotThrowException () {
        Throwable thrown = catchThrowable(() -> {
            featureController.createFeature(feature);
        });
        assertThat(thrown).doesNotThrowAnyException();
    }

    /**
     * Testing update feature with invalid values
     */
    @Test
    public void updateFeature_InvalidRequest_ShouldThrowException () {
        Throwable thrown = catchThrowable(() -> {
            featureController.updateFeature(failedDto);
        });
        assertThat(thrown).isInstanceOf(ResourceNotFoundException.class);
    }

    /**
     * Testing update feature with valid values
     */
    @Test
    public void updateFeature_ValidRequest_ShouldNotThrowException () {
        Throwable thrown = catchThrowable(() -> {
            featureController.updateFeature(dto);
        });
        assertThat(thrown).doesNotThrowAnyException();
    }

    /**
     * Testing retrieve features, there is no chance for exception.
     */
    @Test
    public void retrieveAllFeatures_ShouldNotThrowException () {
        Throwable thrown = catchThrowable(() -> {
            featureController.retrieveAllFeatures();
        });
        assertThat(thrown).doesNotThrowAnyException();
    }
}
