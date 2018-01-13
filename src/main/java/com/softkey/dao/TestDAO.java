/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softkey.dao;

import com.softkey.model.Test;
import com.softkey.utils.ConnectionUtil;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Ahmed Saad
 */
public class TestDAO {

    private final EntityManager em;

    public TestDAO() {
        em = ConnectionUtil.createEMFInstance().createEntityManager();
    }

    public int createTest(Test t) {
        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            return -1;
        }
        return t.getRecid();
    }
    
    public List<Test> getTestList(){
        return em.createNamedQuery("Test.findAll").getResultList();
    }
}
