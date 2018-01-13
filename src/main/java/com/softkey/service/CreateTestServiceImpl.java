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
import java.util.List;

/**
 *
 * @author Ahmed Saad
 */
public class CreateTestServiceImpl implements CreateTestService{

    private final TestDAO testDAO = new TestDAO();
    private final QuizDAO quizDAO = new QuizDAO();
    private final AnswerDAO answerDAO = new AnswerDAO();
    
    @Override
    public int createTest(Test t) {
        return testDAO.createTest(t);
    }

    @Override
    public int createQuiz(Quiz q) {
        return quizDAO.createQuiz(q);
    }

    @Override
    public List<Quiz> getQuizById(int quizId) {
        return quizDAO.getQuizById(quizId);
    }

    @Override
    public List<Quiz> getQuizzes() {
        return quizDAO.getQuizzes();
    }

    @Override
    public void createAnswer(Answer a) {
        answerDAO.createAnswer(a);
    }

    @Override
    public List<Answer> getAnswersByQuiz(int quizId) {
        return answerDAO.getAnswersByQuiz(quizId);
    }
    
}
