package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Answer;
import com.tp.Nile.models.Question;

import java.util.Set;

public interface QAService{
    public Question addQuestion(Question question, Integer productId) throws NullProductIdException, InvalidProductIdException;

    public Answer addAnswer(Answer answer, Integer questionId, Integer userId) throws InvalidQAIdException, NullQAIdException, NullUserException, InvalidUserIdException, NullUserIdException;

    public Set<Question> getQuestions(Integer productId) throws NullProductIdException, InvalidProductIdException;

    public Set<Answer> getAnswers(Integer questionId) throws InvalidQAIdException, NullQAIdException;

    public Question updateVote(Integer questionId, Integer votes) throws InvalidQAIdException, NullQAIdException;

    public  Boolean deleteQue(Integer questionId) throws InvalidQAIdException, NullQAIdException;

    public Boolean deleteAnswer(Integer answerId) throws NullQAIdException, InvalidQAIdException;

    public Question getQuestionById(Integer questionId) throws InvalidQAIdException, NullQAIdException;
}
