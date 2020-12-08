package com.MentorMitrAndroid.QuestionnaireHelper.models;

public class PriorityItem {
    String urgent;
    String notUrgent;

    public PriorityItem(String urgent, String notUrgent) {
        this.urgent = urgent;
        this.notUrgent = notUrgent;
    }

    public PriorityItem() {
        this.urgent = "";
        this.notUrgent = "";
    }

    public String getUrgent() {
        return urgent;
    }

    public void setUrgent(String urgent) {
        this.urgent = urgent;
    }

    public String getNotUrgent() {
        return notUrgent;
    }

    public void setNotUrgent(String notUrgent) {
        this.notUrgent = notUrgent;
    }
}
