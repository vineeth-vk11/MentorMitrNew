package com.MentorMitrAndroid.QuestionnaireHelper.models;

import java.util.List;

public class QuestionModel {
    int serial;
    String question;
    String type;
    boolean mandatory;
    List<String> choices;

    public QuestionModel(int serial, String question, String type, boolean mandatory) {
        this.serial = serial;
        this.question = question;
        this.type = type;
        this.mandatory = mandatory;
        this.choices=null;
    }

    public QuestionModel(int serial, String question, String type, boolean mandatory, List<String> choices) {
        this.serial = serial;
        this.question = question;
        this.type = type;
        this.mandatory = mandatory;
        this.choices = choices;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }
}
