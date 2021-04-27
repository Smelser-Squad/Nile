package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidFeatureIdException;
import com.tp.Nile.exceptions.NullFeatureIdException;
import com.tp.Nile.models.Feature;
import com.tp.Nile.repositories.FeatureRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FeatureServiceImplTests {

    @Mock
    private FeatureRepository repo;

    @InjectMocks
    private FeatureServiceImpl service;

    @Test
    public void testGetAllFeaturesGoldenPath() {
        when(repo.findAll()).thenReturn(List.of(new Feature(1, "Bluetooth", new ArrayList<>())));
        List<Feature> features = service.getAllFeatures();

        assertThat(features)
                .isNotNull()
                .isNotEmpty()
                .allSatisfy(feature -> {
                    assertThat(feature.getFeatureId()).isEqualTo(1);
                    assertThat(feature.getName()).isEqualTo("Bluetooth");
                    assertThat(feature.getProducts().size()).isEqualTo(0);
                });
    }

    @Test
    public void testGetFeatureByIdGoldenPath() {
        when(repo.findById(1)).thenReturn(Optional.of(new Feature(1, "Bluetooth", new ArrayList<>())));
        Feature feature = null;
        try {
            feature = service.getFeatureById(1);
        } catch (NullFeatureIdException | InvalidFeatureIdException ex) {
            fail("Exception thrown");
        }
        assertThat(feature)
                .isNotNull()
                .isInstanceOf(Feature.class)
                .hasFieldOrPropertyWithValue("featureId", 1)
                .hasFieldOrPropertyWithValue("name", "Bluetooth");
        assertThat(feature.getProducts())
                .isNotNull()
                .isEmpty();
    }

    @Test
    public void testGetFeatureByIdNullFeatureId() {
        try {
            Feature feature = service.getFeatureById(null);
            failBecauseExceptionWasNotThrown(NullFeatureIdException.class);
        } catch (InvalidFeatureIdException ex) {
            fail("Wrong exception thrown");
        } catch (NullFeatureIdException ex) {

        }
    }

    @Test
    public void testGetFeatureByIdInvalidFeatureId() {
        when(repo.findById(Integer.MIN_VALUE)).thenReturn(Optional.empty());
        try {
            Feature feature = service.getFeatureById(Integer.MIN_VALUE);
            failBecauseExceptionWasNotThrown(InvalidFeatureIdException.class);
        } catch (NullFeatureIdException ex) {
            fail("Wrong exception thrown");
        } catch (InvalidFeatureIdException ex) {

        }
    }

    @Test
    public void testAddFeatureGoldenPath() {
        int id = 1;
        Feature newFeature = new Feature();
        newFeature.setName("Bluetooth");
        when(repo.saveAndFlush(newFeature)).thenReturn(newFeature);
        Feature addedFeature = null;
        try {
            addedFeature = service.addFeature(newFeature);
        } catch (Exception ex) {
            fail("Exception thrown");
        }
        addedFeature.setFeatureId(id);
        assertThat(addedFeature)
                .isNotNull()
                .isInstanceOf(Feature.class)
                .hasFieldOrPropertyWithValue("featureId", 1)
                .hasFieldOrPropertyWithValue("name", "Bluetooth");
        assertThat(addedFeature.getProducts())
                .isNotNull()
                .isEmpty();
    }

}
