package com.MentorMitrAndroid.QuestionnaireHelper.models;

public class MentorSports {
    String answer;
    String ques;
    int serial;
    public MentorSports(String answer, String ques, int serial) {
        this.answer = answer;
        this.ques = ques;
        this.serial=serial;
    }

    public MentorSports() {
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }
}
