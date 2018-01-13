/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softkey.service;

import com.softkey.dao.AnswerDAO;
import com.softkey.dao.QuizDAO;
import com.softkey.dao.TestDAO;
import com.softkey.model.Answer;
import com.softkey.model.Quiz;
import com.softkey.model.Test;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ahmed Saad
 */
public class TakeTestServiceImpl implements TakeTestService{

    private final QuizDAO quizDAO = new QuizDAO();
    private final TestDAO testDAO = new TestDAO();
    private final AnswerDAO answerDAO = new AnswerDAO();
    
    @Override
    public List<Test> getTestList() {
        return testDAO.getTestList();
    }

    @Override
    public List<Quiz> getQuizByTestId(int testId) {
        return quizDAO.getQuizByTestId(testId);
    }
    
    public int getTestResult(List<Answer> answerList){
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < answerList.size(); i++) {
            if (answerList.get(i).getIsCorrect() == 1)
                result.add(i);
        }
        return result.size();
    }

    @Override
    public Answer getAnswersByRecid(int answerId) {
        return answerDAO.getAnswersByRecid(answerId);
    }
    
}
