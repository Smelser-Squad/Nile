package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvaildProductIdException;
import com.tp.Nile.exceptions.InvaildQAIdException;
import com.tp.Nile.exceptions.NullProductIdException;
import com.tp.Nile.exceptions.NullQAIdException;
import com.tp.Nile.models.Answer;
import com.tp.Nile.models.Question;

import java.util.Set;

public interface QAService{
    public Question addQuestion(Question question, Integer productId) throws NullProductIdException, InvaildProductIdException;

    public Answer addAnswer(Answer answer, Integer questionId, Integer userId) throws InvaildQAIdException, NullQAIdException;

    public Set<Question> getQuestions(Integer productId) throws NullProductIdException, InvaildProductIdException;

    public Set<Answer> getAnswers(Integer questionId) throws InvaildQAIdException, NullQAIdException;

    public Question updateVote(Integer questionId, Integer votes) throws InvaildQAIdException, NullQAIdException;

    public  Boolean deleteQue(Integer questionId) throws InvaildQAIdException, NullQAIdException;

    public Boolean deleteAnswer(Integer answerId) throws NullQAIdException, InvaildQAIdException;

    public Question getQuestionById(Integer questionId) throws InvaildQAIdException, NullQAIdException;
}
