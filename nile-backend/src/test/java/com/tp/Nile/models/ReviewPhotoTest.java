package com.tp.Nile.models;

import com.tp.Nile.repositories.ReviewPhotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class ReviewPhotoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReviewPhotoRepository repository;

    @BeforeEach
    public void setup() {
        repository.deleteAll();
    }

    @Test
    public void save() {
        ReviewPhoto toSave = new ReviewPhoto();
        toSave.setReview(new Review());
        toSave.setImageSrc("http://fakeimage.path/image.png");
        ReviewPhoto toTest = repository.save(toSave);

        assertThat(toTest)
                .isExactlyInstanceOf(ReviewPhoto.class);
        assertThat(toTest.getPhotoId())
                .isExactlyInstanceOf(Integer.class)
                .isEqualTo(1);
        assertThat(toTest.getReview())
                .isExactlyInstanceOf(Review.class);
        assertThat(toTest.getImageSrc())
                .isExactlyInstanceOf(String.class)
                .isEqualTo("http://fakeimage.path/image.png");
    }

//    @Test
//    public void getAll() {
//        ReviewPhoto toSave = new ReviewPhoto();
//        toSave.setReview(new Review());
//        toSave.setImageSrc("http://fakeimage.path/image.png");
//        ReviewPhoto saved = repository.save(toSave);
//        repository.flush();
//        List<ReviewPhoto> toTest = repository.findAll();
//
//        assertThat(toTest)
//                .isExactlyInstanceOf(List.class)
//                .allSatisfy(reviewPhoto -> {
//                    assertThat(reviewPhoto)
//                            .isExactlyInstanceOf(ReviewPhoto.class);
//                    assertThat(reviewPhoto.getPhotoId())
//                            .isExactlyInstanceOf(Integer.class)
//                            .isEqualTo(saved.getPhotoId());
//                    assertThat(reviewPhoto.getReview())
//                            .isExactlyInstanceOf(Review.class)
//                            .isEqualTo(saved.getReview());
//                    assertThat(reviewPhoto.getImageSrc())
//                            .isExactlyInstanceOf(String.class)
//                            .isEqualTo(saved.getImageSrc());
//                });
//    }
}
