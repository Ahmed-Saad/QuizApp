/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softkey.dao;

import com.softkey.model.Answer;
import com.softkey.utils.ConnectionUtil;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Ahmed Saad
 */
public class AnswerDAO {

    private final EntityManager em;

    public AnswerDAO() {
        em = ConnectionUtil.createEMFInstance().createEntityManager();
    }

    public void createAnswer(Answer a) {
        try {
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Answer> getAnswersByQuiz(int quizId){
        return em.createNamedQuery("Answer.findByQuizRecid").setParameter("quizRecid", quizId).getResultList();
    }
    
    public Answer getAnswersByRecid(int answerId){
        return (Answer) em.createNamedQuery("Answer.findByRecid").setParameter("recid", answerId).getSingleResult();
    }
}
