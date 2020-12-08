package com.MentorMitrAndroid.QuestionnaireHelper.models;

public class NoOfHours {
    String activity;
    String time;

    public NoOfHours(String activity, String time) {
        this.activity = activity;
        this.time = time;
    }

    public NoOfHours() {
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
