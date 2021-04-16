package com.tp.Nile.controllers;


import com.tp.Nile.exceptions.InvalidProductIdException;
import com.tp.Nile.exceptions.InvalidQAIdException;
import com.tp.Nile.exceptions.NullProductIdException;
import com.tp.Nile.exceptions.NullQAIdException;
import com.tp.Nile.models.Answer;
import com.tp.Nile.models.Question;
import com.tp.Nile.services.QAServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/reviews")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class QAController {

    @Autowired
    QAServiceImpl service;

    //post question
    @PostMapping("/addQuestion/{productId}")
    public ResponseEntity addQuestion(@RequestBody Question question, @PathVariable Integer productId){
        try {
            return ResponseEntity.ok(service.addQuestion(question, productId));
        }catch (NullProductIdException | InvalidProductIdException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    //post answer to question
    @PostMapping("/addAnswer/{questionId}/{userId}")
    public ResponseEntity addAnswer(@RequestBody Answer answer, @PathVariable("questionId") Integer questionId, @PathVariable("userId") Integer userId){
        try {
            return ResponseEntity.ok(service.addAnswer(answer, questionId, userId));
        } catch (InvalidQAIdException | NullQAIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //get question/votes based on product id
    @GetMapping("/questions/{productId}")
    public ResponseEntity getQuestions(@PathVariable Integer productId){
        try {
            return ResponseEntity.ok(service.getQuestions(productId));
        } catch (NullProductIdException | InvalidProductIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //get answers based on question
    @GetMapping("/getAnswers/{questionId}")
    public ResponseEntity getAnswers(@PathVariable Integer questionId){
        try {
            return ResponseEntity.ok(service.getAnswers(questionId));
        } catch (InvalidQAIdException | NullQAIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //update votes on question
    @PutMapping("/updateVotes/{questionId}/{votes}")
    public ResponseEntity updateVote(@PathVariable("questionId") Integer questionId, @PathVariable("votes") Integer votes){
        try {
            return ResponseEntity.ok(service.updateVote(questionId, votes));
        } catch (InvalidQAIdException | NullQAIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //delete question for admin
    @DeleteMapping("/deleteQue/{questionId}")
    public ResponseEntity deleteQuestion(@PathVariable Integer questionId){
        try {
            return ResponseEntity.ok(service.deleteQue(questionId));
        } catch (InvalidQAIdException | NullQAIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //delete Answer for admin
    @DeleteMapping("/deleteAns/{answerId}")
    public ResponseEntity deleteAnswer(@PathVariable Integer answerId){
        try {
            return ResponseEntity.ok(service.deleteAnswer(answerId));
        } catch (InvalidQAIdException | NullQAIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
