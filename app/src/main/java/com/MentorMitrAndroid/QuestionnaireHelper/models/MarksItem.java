package com.MentorMitrAndroid.QuestionnaireHelper.models;

public class MarksItem {
    String subject;
    int marks;

    public MarksItem(String subject) {
        this.subject = subject;
        this.marks = 0;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}
