package com.tp.Nile.repositories;

import com.tp.Nile.models.Answer;
import com.tp.Nile.models.Question;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
@Profile({ "dev", "test" })
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    Set<Answer> findByQuestion(Question question);

}
