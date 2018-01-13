/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softkey.managedbeans;

import com.softkey.model.Answer;
import com.softkey.model.Quiz;
import com.softkey.model.Test;
import com.softkey.service.TakeTestServiceImpl;
import com.softkey.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ahmed Saad
 */
@ManagedBean(name = "testMB")
@ViewScoped
public class TestMB {

    private TakeTestServiceImpl service;
    private List<Quiz> quizList;
    private Test testObj;
    private Answer selectedAnswer;
    private List<Answer> answerList;
    private int myResult;

    @PostConstruct
    public void init() {
        service = new TakeTestServiceImpl();
        testObj = (Test) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("testObj");
        if (Utils.isNotEmpty(testObj)) {
            quizList = service.getQuizByTestId(testObj.getRecid());
        }
        answerList = new ArrayList<>();
    }
    
    @PreDestroy
    public void distroy() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
    }

    public void handleAnswers() {
        answerList.add(selectedAnswer);
        System.out.println("---------"+selectedAnswer.getAnswer()+"------------");
        selectedAnswer = new Answer();
    }

    public void finishTest() {
        myResult = service.getTestResult(answerList);
        System.out.println("----------------"+myResult+" and list size is "+answerList.size());
        answerList = new ArrayList<>();
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    public Test getTestObj() {
        return testObj;
    }

    public void setTestObj(Test testObj) {
        this.testObj = testObj;
    }

    public Answer getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(Answer selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }

    public int getMyResult() {
        return myResult;
    }

    public void setMyResult(int myResult) {
        this.myResult = myResult;
    }

}
