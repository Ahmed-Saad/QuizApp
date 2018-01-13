/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softkey.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ahmed Saad
 */
@Entity
@Table(name = "quiz")
@NamedQueries({
    @NamedQuery(name = "Quiz.findAll", query = "SELECT q FROM Quiz q")
    , @NamedQuery(name = "Quiz.findByRecid", query = "SELECT q FROM Quiz q WHERE q.recid = :recid")
    , @NamedQuery(name = "Quiz.findByQuestion", query = "SELECT q FROM Quiz q WHERE q.question = :question")
    , @NamedQuery(name = "Quiz.findByTestRecid", query = "SELECT q FROM Quiz q WHERE q.testrecid.recid = :testId")
    , @NamedQuery(name = "Quiz.findByQuizImage", query = "SELECT q FROM Quiz q WHERE q.quizImage = :quizImage")})
public class Quiz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recid")
    private Integer recid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "question")
    private String question;
    @Size(max = 500)
    @Column(name = "quiz_image")
    private String quizImage;
    @JoinColumn(name = "Test_recid", referencedColumnName = "recid")
    @ManyToOne(optional = false)
    private Test testrecid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "quizRecid")
    private List<Answer> answerList;

    public Quiz() {
    }

    public Quiz(Integer recid) {
        this.recid = recid;
    }

    public Quiz(Integer recid, String question) {
        this.recid = recid;
        this.question = question;
    }

    public Integer getRecid() {
        return recid;
    }

    public void setRecid(Integer recid) {
        this.recid = recid;
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

    public Test getTestrecid() {
        return testrecid;
    }

    public void setTestrecid(Test testrecid) {
        this.testrecid = testrecid;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (recid != null ? recid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quiz)) {
            return false;
        }
        Quiz other = (Quiz) object;
        if ((this.recid == null && other.recid != null) || (this.recid != null && !this.recid.equals(other.recid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softkey.model.Quiz[ recid=" + recid + " ]";
    }

}
