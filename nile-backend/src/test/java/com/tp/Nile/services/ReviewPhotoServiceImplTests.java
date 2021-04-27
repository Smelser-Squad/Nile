package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.*;
import com.tp.Nile.repositories.ReviewPhotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class ReviewPhotoServiceImplTests {
    @Mock
    ReviewPhotoRepository repository;

    @Mock
    ReviewServiceImpl reviewService;

    @InjectMocks
    ReviewPhotoServiceImpl service;

    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }

    @Test
    public void getAllReviewPhotos() {
        when(repository.findAll())
                .thenReturn(
                        List.of(new ReviewPhoto(1, new Review(), "http://fakeimage.path/image.png"))
                );

        List<ReviewPhoto> toTest = service.getAllPhotos();

        assertThat(toTest)
                .isNotNull()
                .isNotEmpty()
                .allSatisfy(reviewPhoto -> {
                    assertThat(reviewPhoto)
                            .isExactlyInstanceOf(ReviewPhoto.class);
                    assertThat(reviewPhoto.getPhotoId())
                            .isEqualTo(1)
                            .isExactlyInstanceOf(Integer.class);
                    assertThat(reviewPhoto.getReview())
                            .isExactlyInstanceOf(Review.class);
                    assertThat(reviewPhoto.getImageSrc())
                            .isEqualTo("http://fakeimage.path/image.png")
                            .isExactlyInstanceOf(String.class);
                });
    }

    @Test
    public void getReviewPhotoById() throws InvalidPhotoIdException {
        when(repository.findById(anyInt()))
                .thenReturn(
                        Optional.of(
                                new ReviewPhoto(1, new Review(), "http://fakeimage.path/image.png")
                        )
                );

        ReviewPhoto toTest = service.getPhotoById(1);

        assertThat(toTest)
                .isExactlyInstanceOf(ReviewPhoto.class);
        assertThat(toTest.getPhotoId())
                .isExactlyInstanceOf(Integer.class);
        assertThat(toTest.getReview())
                .isExactlyInstanceOf(Review.class);
        assertThat(toTest.getImageSrc())
                .isExactlyInstanceOf(String.class);
    }

    @Test
    public void getPhotosByReview()
            throws NullReviewAttributeException, NullReviewIdException, InvalidReviewIdException {
        Review testReview = new Review(
                1,
                new User(),
                "Test Summary",
                "Test Title",
                LocalDate.of(2020, 1, 1),
                new ArrayList<ReviewPhoto>(),
                new Product(),
                new Feature(),
                5,
                true);
        testReview.setReviewPhotos(List.of(
                new ReviewPhoto(1, testReview, "http://fakeimage.path/image.png")
        ));
        when(reviewService.getReviewById(anyInt()))
                .thenReturn(testReview);

        List<ReviewPhoto> toTest = service.getPhotosByReview(1);

        assertThat(toTest)
                .isNotNull()
                .isNotEmpty()
                .allSatisfy(reviewPhoto -> {
                    assertThat(reviewPhoto)
                            .isExactlyInstanceOf(ReviewPhoto.class);
                    assertThat(reviewPhoto.getPhotoId())
                            .isExactlyInstanceOf(Integer.class)
                            .isEqualTo(1);
                    assertThat(reviewPhoto.getReview())
                            .isExactlyInstanceOf(Review.class)
                            .isEqualTo(testReview);
                    assertThat(reviewPhoto.getImageSrc())
                            .isExactlyInstanceOf(String.class)
                            .isEqualTo("http://fakeimage.path/image.png");
                });
    }

    @Test
    public void updatePhoto() throws InvalidPhotoIdException {
        ReviewPhoto firstPhoto = new ReviewPhoto(1, new Review(), "http://fakeimage.path/image.png");
        ReviewPhoto updatedPhoto = new ReviewPhoto(1, new Review(), "http://fakeimage.path/TEST.png");

        when(repository.findById(anyInt())).thenReturn(Optional.of(firstPhoto));
        when(repository.saveAndFlush(any(ReviewPhoto.class))).thenReturn(updatedPhoto);

        ReviewPhoto toTest = service.updatePhoto(updatedPhoto);

        assertThat(toTest)
                .isExactlyInstanceOf(ReviewPhoto.class)
                .isEqualTo(updatedPhoto);
        assertThat(toTest.getPhotoId())
                .isExactlyInstanceOf(Integer.class)
                .isEqualTo(1);
        assertThat(toTest.getReview())
                .isExactlyInstanceOf(Review.class);
        assertThat(toTest.getImageSrc())
                .isExactlyInstanceOf(String.class)
                .isEqualTo("http://fakeimage.path/TEST.png")
                .isNotEqualTo("http://fakeimage.path/image.png");
    }

    @Test
    public void deletePhoto() throws NullPhotoIdException, InvalidPhotoIdException {
        ReviewPhoto toDelete = new ReviewPhoto(1, new Review(), "http://fakeimage.path/image.png");
        when(repository.findById(anyInt()))
                .thenReturn(Optional.of(toDelete));

        assertThat(service.deletePhoto(toDelete.getPhotoId())).isTrue();
        verify(repository, times(1)).delete(toDelete);
    }

}
