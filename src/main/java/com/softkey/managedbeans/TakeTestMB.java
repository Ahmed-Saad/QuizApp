/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softkey.managedbeans;

import com.softkey.model.Test;
import com.softkey.service.TakeTestServiceImpl;
import com.softkey.utils.Utils;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ahmed Saad
 */
@ManagedBean(name = "takeTestMB")
@SessionScoped
public class TakeTestMB {

    private TakeTestServiceImpl service;
    private List<Test> testList;

    @PostConstruct
    public void init() {
        service = new TakeTestServiceImpl();
        testList = service.getTestList();
    }

    public void selectTest(Test test) {
        if (Utils.isNotEmpty(test)) {
            try {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().getSessionMap().put("testObj", test);
                context.getExternalContext().redirect("test.xhtml");
            } catch (Exception e) {
            }
        }
    }

    public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }
}
