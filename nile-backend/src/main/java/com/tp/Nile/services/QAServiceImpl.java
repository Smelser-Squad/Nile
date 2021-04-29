package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidProductIdException;
import com.tp.Nile.exceptions.InvalidQAIdException;
import com.tp.Nile.exceptions.NullProductIdException;
import com.tp.Nile.exceptions.NullQAIdException;
import com.tp.Nile.models.Answer;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.Question;
import com.tp.Nile.repositories.AnswerRepository;
import com.tp.Nile.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    @Override
    public Question addQuestion(Question question, Integer productId) throws NullProductIdException, InvalidProductIdException {
        Product pro = pRepo.getProductById(productId);
        question.setProduct(pro);
        return qRepo.saveAndFlush(question);
    }

    @Override
    public Answer addAnswer(Answer answer, Integer questionId, Integer userId) throws InvalidQAIdException, NullQAIdException {
        Question que = this.getQuestionById(questionId);
        //TODO: NEED USER SERVICE
        answer.setQuestion(que);
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
}
