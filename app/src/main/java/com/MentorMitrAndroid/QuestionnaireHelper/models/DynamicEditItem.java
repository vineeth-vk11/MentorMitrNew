package com.MentorMitrAndroid.QuestionnaireHelper.models;

public class DynamicEditItem {
    String string;

    public DynamicEditItem(String string) {
        this.string = string;
    }
    public DynamicEditItem() {
        this.string = "";
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
