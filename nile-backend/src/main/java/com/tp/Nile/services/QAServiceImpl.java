package com.tp.Nile.services;

import com.tp.Nile.repositories.AnswerRepository;
import com.tp.Nile.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QAServiceImpl implements QAService{

    @Autowired
    QuestionRepository QRepo;

    @Autowired
    AnswerRepository ARepo;



}
