/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softkey.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ahmed Saad
 */
@Entity
@Table(name = "answer")
@NamedQueries({
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a")
    , @NamedQuery(name = "Answer.findByRecid", query = "SELECT a FROM Answer a WHERE a.recid = :recid")
    , @NamedQuery(name = "Answer.findByIsCorrect", query = "SELECT a FROM Answer a WHERE a.isCorrect = :isCorrect")
    , @NamedQuery(name = "Answer.findByAnswer", query = "SELECT a FROM Answer a WHERE a.answer = :answer")
    , @NamedQuery(name = "Answer.findByQuizRecid", query = "SELECT a FROM Answer a WHERE a.quizRecid = :quizRecid")
    , @NamedQuery(name = "Answer.findByAnswerMage", query = "SELECT a FROM Answer a WHERE a.answerMage = :answerMage")})
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "recid")
    private Integer recid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_correct")
    private int isCorrect;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "answer")
    private String answer;
    @Size(max = 500)
    @Column(name = "answer_mage")
    private String answerMage; //answerImage
    @JoinColumn(name = "quiz_recid", referencedColumnName = "recid")
    @ManyToOne(optional = false)
    private Quiz quizRecid;

    public Answer() {
    }

    public Answer(Integer recid) {
        this.recid = recid;
    }

    public Answer(Integer recid, int isCorrect, String answer) {
        this.recid = recid;
        this.isCorrect = isCorrect;
        this.answer = answer;
    }

    public Answer(int isCorrect, String answer, String answerMage) {
        this.isCorrect = isCorrect;
        this.answer = answer;
        this.answerMage = answerMage;
    }

    public Integer getRecid() {
        return recid;
    }

    public void setRecid(Integer recid) {
        this.recid = recid;
    }

    public int getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(int isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerMage() {
        return answerMage;
    }

    public void setAnswerMage(String answerMage) {
        this.answerMage = answerMage;
    }

    public Quiz getQuizRecid() {
        return quizRecid;
    }

    public void setQuizRecid(Quiz quizRecid) {
        this.quizRecid = quizRecid;
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
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        if ((this.recid == null && other.recid != null) || (this.recid != null && !this.recid.equals(other.recid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.softkey.model.Answer[ recid=" + recid + " ]";
    }

}
