package com.tp.Nile.controllers;


import com.tp.Nile.models.Answer;
import com.tp.Nile.models.Question;
import com.tp.Nile.services.QAServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class QAController {

    @Autowired
    QAServiceImpl service;

    //post question
    @PostMapping("/addQuestion")
    public ResponseEntity addQuestion(@RequestBody Question question){
        return ResponseEntity.ok(service.addQuestion(question));
    }


    //post answer to question
    @PostMapping("/addAnswer")
    public ResponseEntity addAnswer(@RequestBody Answer answer){
        return ResponseEntity.ok(service.addAnswer(answer));
    }

    //get question/votes based on product id
    @GetMapping("/questions/{productId}")
    public ResponseEntity getQuestions(@PathVariable Integer productId){
        return ResponseEntity.ok(service.getQuestions(productId));
    }

    //get answers based on question
    @GetMapping("/getAnswers/{questionId}")
    public ResponseEntity getAnswers(@PathVariable Integer questionId){
        return ResponseEntity.ok(service.getAnswers(questionId));
    }

    //update votes on question
    @PutMapping("/updateVotes/{questionId}/{votes}")
    public ResponseEntity updateVote(@PathVariable("questionId") Integer questionId, @PathVariable("votes") Integer votes){
        return ResponseEntity.ok(service.updateVote(questionId, votes));
    }

    //delete question for admin
    

    //delete Answer for admin

}
