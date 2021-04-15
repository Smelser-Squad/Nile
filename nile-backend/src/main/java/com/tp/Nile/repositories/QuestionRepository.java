package com.tp.Nile.repositories;

import com.tp.Nile.models.Product;
import com.tp.Nile.models.Question;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Profile({ "dev", "test" })
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    Set<Question> findByProduct(Product product);
}
