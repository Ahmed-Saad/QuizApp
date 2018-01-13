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
public interface TakeTestService {
    
    public List<Test> getTestList();
    public List<Quiz> getQuizByTestId(int testId);
    public Answer getAnswersByRecid(int answerId);
    
}
