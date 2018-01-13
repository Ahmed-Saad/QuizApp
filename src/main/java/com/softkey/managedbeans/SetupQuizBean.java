/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softkey.managedbeans;

import com.softkey.model.Answer;
import com.softkey.model.Quiz;
import com.softkey.model.Test;
import com.softkey.service.CreateTestServiceImpl;
import com.softkey.utils.Utils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Ahmed Saad
 */
@ManagedBean(name = "setupQuizMB")
@ViewScoped
public class SetupQuizBean {

    private int testId;
    private CreateTestServiceImpl service;
    private String question;
    private String quizImage;
    private String answer;
    private boolean isCorrect;
    private boolean isSelected;
    private String answerImage;
    private Quiz quiz;
    private List<Answer> answerList;
    int checked = 0;
    boolean qImgUploaded;
    boolean hasImage;
    private boolean showUpload;
    private int quizNo;

    @PostConstruct
    public void init() {
        service = new CreateTestServiceImpl();
        int s = (Integer) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("testId");
        if (Utils.isNotEmpty(s)) {
            testId = s;
        }
        answerList = new ArrayList<>();
    }

    @PreDestroy
    public void distroy() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
    }

    public void showAnsUpload() {
        if (showUpload) {
            showUpload = false;
        } else {
            showUpload = true;
        }

    }

    public void createQuiz() {
        if (answerList.size() == 4) {
            quiz = new Quiz();
            quiz.setQuestion(question);
            quiz.setQuizImage(quizImage);
            quiz.setTestrecid(new Test(testId));
            for (int i = 0; i < answerList.size(); i++) {
                answerList.get(i).setQuizRecid(quiz);
            }
            quiz.setAnswerList(answerList);
            service.createQuiz(quiz);
            question = "";
            answer = "";
            quizImage = "";
            isSelected = false;
            hasImage = false;
            qImgUploaded = false;
            answerList = new ArrayList<>();
            quizNo++;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Successful", "Quistition Added"));
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Info", "Quistion answers must be four answers"));
        }
    }

    public void prepareAnswers() {
        int correct = 0;
        if (isCorrect) {
            correct = 1;
            checked = 1;
            isSelected = true;
        }
        if (checked != 1 && answerList.size() == 3) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Info", "This answer must checked as correct"));
            return;
        }
        if (answerList.size() == 4) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Info", "You can only add 4 answers"));
            return;
        }
        if (hasImage && Utils.isEmptyString(answerImage)) {
            if (correct == 1) {
                isSelected = false;
            }
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Info", "All answers must have images"));
            return;
        }
        answerList.add(new Answer(correct, answer, answerImage));
        isCorrect = false;
        answer = "";
        answerImage = "";
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", "Answer Added"));
    }

    public void quizImageUpload(FileUploadEvent event) {
        if (!qImgUploaded) {
            quizImage = Utils.uploadFile(event, "quiz-");
            qImgUploaded = true;
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Info", "You can only upload 1 image"));
        }
    }

    public void answerImageUpload(FileUploadEvent event) {
        if (Utils.isEmpty(answerList)) {
            hasImage = true;
        }
        if (hasImage) {
            answerImage = Utils.uploadFile(event, "ans-");
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Info", "You can't add image becauze first answer doesn't have image"));
        }
    }

    public void createNewTest() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().invalidateSession();
            context.getExternalContext().redirect("create-test.xhtml");
        } catch (Exception e) {
        }
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuizImage() {
        return quizImage;
    }

    public void setQuizImage(String quizImage) {
        this.quizImage = quizImage;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getAnswerImage() {
        return answerImage;
    }

    public void setAnswerImage(String answerImage) {
        this.answerImage = answerImage;
    }

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public boolean isShowUpload() {
        return showUpload;
    }

    public void setShowUpload(boolean showUpload) {
        this.showUpload = showUpload;
    }

    public int getQuizNo() {
        return quizNo;
    }
}
