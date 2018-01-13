/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softkey.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ahmed Saad
 */
public class ConnectionUtil {
    
    private static EntityManagerFactory emf;
    
    static {
        try {
            emf = Persistence.createEntityManagerFactory("Quiz_unit");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static EntityManagerFactory createEMFInstance(){
        return emf;
    }
}
