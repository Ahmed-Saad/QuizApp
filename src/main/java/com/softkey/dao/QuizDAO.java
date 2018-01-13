/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softkey.dao;

import com.softkey.model.Quiz;
import com.softkey.utils.ConnectionUtil;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Ahmed Saad
 */
public class QuizDAO {
    
    private final EntityManager em;

    public QuizDAO() {
        em = ConnectionUtil.createEMFInstance().createEntityManager();
    }
    
    public int createQuiz(Quiz q){
        try {
            em.getTransaction().begin();
            em.persist(q);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return q.getRecid();
    }
    
    public List<Quiz> getQuizById(int quizId){
        return em.createNamedQuery("Quiz.findByRecid").setParameter("recid", quizId).getResultList();
    }
    
    public List<Quiz> getQuizByTestId(int testId){
        return em.createNamedQuery("Quiz.findByTestRecid").setParameter("testId", testId).getResultList();
    }
    
    public List<Quiz> getQuizzes(){
        return em.createNamedQuery("Quiz.findAll").getResultList();
    }
    
}
