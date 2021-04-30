package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Answer;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.Question;
import com.tp.Nile.repositories.AnswerRepository;
import com.tp.Nile.repositories.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class QAServiceImplTest {

    @Mock
    private QuestionRepository qRepo;

    @Mock
    private AnswerRepository aRepo;

    @Mock
    private ProductServiceImpl pService;

    @InjectMocks
    private QAServiceImpl service;

    @Test
    public void addQuestionGoldenPath(){
        Question quest = new Question();
        quest.setVotes(3);
        quest.setQuestion("question1");
        when(qRepo.saveAndFlush(quest)).thenReturn(quest);
        Question addedQuest = null;
        try{
            addedQuest = service.addQuestion(quest, 1);
        } catch (NullProductIdException | InvalidProductIdException e) {
            fail("Exception thrown");
        }
        addedQuest.setQuestionId(1);
        assertThat(addedQuest).isNotNull()
                .isInstanceOf(Question.class)
                .hasFieldOrPropertyWithValue("questionId",1)
                .hasFieldOrPropertyWithValue("question", "question1")
                .hasFieldOrPropertyWithValue("votes",3);
    }

    //TODO still need to add user verification for this method
    @Test
    public void addAnswerGoldenPathTest(){
        Question quest = new Question();
        quest.setVotes(3);
        quest.setQuestion("question2");
        Answer ans = new Answer();
        ans.setAnswer("answer2");
        when(qRepo.findById(1)).thenReturn(java.util.Optional.of(quest));
        when(aRepo.saveAndFlush(ans)).thenReturn(ans);
        Answer addedAnswer = null;
        try {
            addedAnswer = service.addAnswer(ans,1,1);
        } catch (InvalidQAIdException | NullQAIdException | NullUserException | InvalidUserIdException | NullUserIdException e) {
            fail("Exception failed");
        }
        addedAnswer.setAnswerId(1);
        //addedAnswer.setUser();
        assertThat(addedAnswer).isNotNull()
                .hasFieldOrPropertyWithValue("answerId",1)
                .hasFieldOrPropertyWithValue("answer", "answer2")
                .hasFieldOrPropertyWithValue("question", quest);
    }

    @Test
    public void getQuestionsGoldenPathTest(){
        Question quest = new Question();
        quest.setQuestion("question3");
        quest.setQuestionId(3);
        quest.setVotes(3);
        Product prod = new Product();
        prod.setProductId(1);
        Set<Question> addedQuestions = new HashSet<>();
        addedQuestions.add(quest);
        try {
            when(pService.getProductById(1)).thenReturn(prod);
            when(qRepo.findByProductOrderByVotesDesc(prod)).thenReturn(addedQuestions);
            addedQuestions = service.getQuestions(1);
        } catch (InvalidProductIdException | NullProductIdException e) {
            fail("Exception failed");
        }
        assertThat(addedQuestions).isNotNull();
    }

    //Todo: Need to add user
    @Test
    public void getAnswersGoldenPathTest(){
        Question quest = new Question();
        quest.setQuestion("question4");
        quest.setQuestionId(4);
        quest.setVotes(4);
        quest.setQuestionId(4);
        Product prod = new Product();
        prod.setProductId(1);
        Answer ans = new Answer();
        ans.setQuestion(quest);
        ans.setAnswer("answer4");
        Set<Answer> addedAnswers = new HashSet<>();
        addedAnswers.add(ans);
        Set<Question> addedQuestions = new HashSet<>();
        addedQuestions.add(quest);

        try {
            when(qRepo.findById(4)).thenReturn(java.util.Optional.of(quest));
            when(aRepo.findByQuestion(quest)).thenReturn(addedAnswers);
            addedAnswers = service.getAnswers(4);
        } catch (NullQAIdException | InvalidQAIdException e) {
            fail("Exception failed");
        }
        assertThat(addedAnswers).isNotNull();
    }

    @Test
    public void updateVoteGoldenPathTest(){
        Question quest = new Question();
        quest.setQuestion("question5");
        quest.setQuestionId(5);
        quest.setVotes(4);
        quest.setQuestionId(5);
        Product prod = new Product();
        prod.setProductId(1);
        Question addedQuestion = new Question();
        int votes = 5;
        try {
            when(qRepo.findById(5)).thenReturn(java.util.Optional.of(quest));
            when(qRepo.saveAndFlush(quest)).thenReturn(quest);
            addedQuestion = service.updateVote(5,votes);
        } catch (NullQAIdException | InvalidQAIdException e) {
            fail("Exception failed");
        }
        assertThat(addedQuestion).isNotNull()
                .isInstanceOf(Question.class)
                .hasFieldOrPropertyWithValue("questionId",5)
                .hasFieldOrPropertyWithValue("question", "question5")
                .hasFieldOrPropertyWithValue("votes",5);
    }

    @Test
    public void deleteQueGoldenPathTest(){
        Question quest = new Question();
        quest.setQuestion("question6");
        quest.setQuestionId(6);
        quest.setVotes(6);
        quest.setQuestionId(6);
        Product prod = new Product();
        prod.setProductId(1);
        boolean response = false;
        try {
            when(qRepo.findById(6)).thenReturn(java.util.Optional.of(quest));
            response = service.deleteQue(6);
        } catch (NullQAIdException | InvalidQAIdException e) {
            fail("Exception failed");
        }
        assertThat(response).isTrue();
    }

    @Test
    public void deleteAnswerGoldenPathTest(){
        Answer ans = new Answer();
        ans.setAnswer("Answer7");
        ans.setAnswerId(7);
        boolean response = false;
        try {
            when(aRepo.findById(7)).thenReturn(java.util.Optional.of(ans));
            response = service.deleteAnswer(7);
        } catch (NullQAIdException | InvalidQAIdException e) {
            fail("Exception failed");
        }
        assertThat(response).isTrue();
    }

    @Test
    public void getQuestionByIdGoldenPathTest(){
        Question quest = new Question();
        quest.setQuestion("question8");
        quest.setQuestionId(8);
        quest.setVotes(8);
        quest.setQuestionId(8);
        Product prod = new Product();
        prod.setProductId(1);
        Question response = null;
        try {
            when(qRepo.findById(8)).thenReturn(java.util.Optional.of(quest));
            response = service.getQuestionById(8);
        } catch (NullQAIdException | InvalidQAIdException e) {
            fail("Exception failed");
        }
        assertThat(response).isNotNull()
                .isInstanceOf(Question.class)
                .hasFieldOrPropertyWithValue("questionId",8)
                .hasFieldOrPropertyWithValue("question", "question8")
                .hasFieldOrPropertyWithValue("votes",8);
    }
}
