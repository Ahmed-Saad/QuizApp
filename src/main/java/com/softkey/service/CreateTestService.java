/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softkey.service;

import com.softkey.model.Answer;
import com.softkey.model.Quiz;
import com.softkey.model.Test;
import java.util.List;

/**
 *
 * @author Ahmed Saad
 */
public interface CreateTestService {
    
    // Test Services
    public int createTest(Test t);
    
    
    //Quiz Services
    public int createQuiz(Quiz q);
    public List<Quiz> getQuizById(int quizId);
    public List<Quiz> getQuizzes();
    
    //Answer Services
    public void createAnswer(Answer a);
    public List<Answer> getAnswersByQuiz(int quizId);
    
}
