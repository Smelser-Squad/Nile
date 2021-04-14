package com.tp.Nile.repositories;

import com.tp.Nile.models.Answer;
import com.tp.Nile.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    Set<Answer> findByQuestion(Question question);

}
