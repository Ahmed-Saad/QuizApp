/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softkey.managedbeans;

import com.softkey.model.Test;
import com.softkey.service.CreateTestServiceImpl;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ahmed Saad
 */
@ManagedBean(name = "createTestMB")
@SessionScoped
public class CreateTestMB {

    private CreateTestServiceImpl service;
    private String testName;
    private Test test;
    private int testId;
    private int ParamTestId;

    @PostConstruct
    public void init() {
        service = new CreateTestServiceImpl();
        test = new Test();
    }

    public void cretaeTest() {
        test.setTestName(testName);
        try {
            testId = service.createTest(test);
            if (testId != -1) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getSessionMap().put("testId", testId);
                context.getExternalContext().redirect("setup-quiz.xhtml");
            }
        } catch (Exception e) {
        }

    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getParamTestId() {
        return ParamTestId;
    }

    public void setParamTestId(int ParamTestId) {
        this.ParamTestId = ParamTestId;
    }

}
