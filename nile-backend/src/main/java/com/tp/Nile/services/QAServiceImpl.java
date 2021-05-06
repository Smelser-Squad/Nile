package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Answer;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.Question;
import com.tp.Nile.models.User;
import com.tp.Nile.repositories.AnswerRepository;
import com.tp.Nile.repositories.QuestionRepository;
import com.tp.Nile.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;
import java.util.Set;

@Service
public class QAServiceImpl implements QAService{

    @Autowired
    QuestionRepository qRepo;

    @Autowired
    AnswerRepository aRepo;

    @Autowired
    ProductServiceImpl pRepo;

    @Autowired
    UserRepository uRepo;


    @Override
    public Question addQuestion(Question question, Integer productId) throws NullProductIdException, InvalidProductIdException {
        Product pro = pRepo.getProductById(productId);
        question.setProduct(pro);
        question.setQuestion(question.getQuestion().toLowerCase(Locale.ROOT));
        return qRepo.saveAndFlush(question);
    }

    @Override
    public Answer addAnswer(Answer answer, Integer questionId, Integer userId) throws InvalidQAIdException, NullQAIdException, NullUserException, InvalidUserIdException, NullUserIdException {
        Question que = this.getQuestionById(questionId);
//        Long user = new Long(userId);
//        User usuario = this.getUserById(user);
//        answer.setUser(usuario);
        answer.setQuestion(que);
        answer.setAnswer(answer.getAnswer().toLowerCase(Locale.ROOT));
        return aRepo.saveAndFlush(answer);

    }

    @Override
    public Set<Question> getQuestions(Integer productId) throws NullProductIdException, InvalidProductIdException {
        Product pro = pRepo.getProductById(productId);
        Set<Question> que = qRepo.findByProductOrderByVotesDesc(pro);
        if(!que.isEmpty()){
            return que;
        }
        return null;
    }

    @Override
    public Set<Answer> getAnswers(Integer questionId) throws InvalidQAIdException, NullQAIdException {
        Question que = this.getQuestionById(questionId);
        return aRepo.findByQuestion(que);
    }

    @Override
    public Question updateVote(Integer questionId, Integer votes) throws InvalidQAIdException, NullQAIdException {
        Question que = this.getQuestionById(questionId);
        que.setVotes(votes);
        return qRepo.saveAndFlush(que);

    }

    @Override
    public Boolean deleteQue(Integer questionId) throws InvalidQAIdException, NullQAIdException {
        Question que = this.getQuestionById(questionId);
        qRepo.deleteById(questionId);
        return true;
    }

    @Override
    public Boolean deleteAnswer(Integer answerId) throws NullQAIdException, InvalidQAIdException {
        if(answerId == null){
            throw new NullQAIdException("Cannot delete Answer with null id");
        }
        Optional<Answer> ans = aRepo.findById(answerId);
        if(ans.isPresent()) {
            aRepo.deleteById(answerId);
            return true;
        }
        throw new InvalidQAIdException("Cannot delete answer");
    }

    @Override
    public Question getQuestionById(Integer questionId) throws InvalidQAIdException, NullQAIdException {
        if(questionId==null){
            throw new NullQAIdException("Cannot get question with null id");
        }
        Question toReturn = null;
        Optional<Question> question = qRepo.findById(questionId);
        if(question.isPresent()){
            toReturn = question.get();
            return toReturn;
        }else{
            throw new InvalidQAIdException("Question with that id does not exist");
        }
    }

    @Override
    public User getUserById(Long userId) throws NullUserException, NullUserIdException, InvalidUserIdException {
        if (userId == null)
        {
            throw new NullUserIdException("User id can not be null");
        }

        User retrieved = null;

        Optional<User> user = uRepo.findById(userId);

        if(user.isPresent()){
            retrieved=user.get();
            if (retrieved == null)
                throw new NullUserException("All non nullable attributes must have value");
            else
                return retrieved;
        }else{
            throw new InvalidUserIdException("User with that id does not exist");
        }

    }
}
