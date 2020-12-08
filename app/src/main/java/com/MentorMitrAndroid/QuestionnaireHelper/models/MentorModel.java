package com.MentorMitrAndroid.QuestionnaireHelper.models;

public class MentorModel {
    String name;
    String email;
    String uid;


    public MentorModel(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public  MentorModel()
    {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
