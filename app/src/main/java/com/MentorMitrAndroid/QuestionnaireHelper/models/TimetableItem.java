package com.MentorMitrAndroid.QuestionnaireHelper.models;

public class TimetableItem {
    String subject;
    boolean isSet;

    public TimetableItem() {}

    public TimetableItem(String subject, boolean isSet) {
        this.subject = subject;
        this.isSet = isSet;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isSet() {
        return isSet;
    }

    public void setSet(boolean set) {
        isSet = set;
    }
}
