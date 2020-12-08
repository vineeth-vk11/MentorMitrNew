package com.MentorMitrAndroid.QuestionnaireHelper.models;

import com.MentorMitrAndroid.QuestionnaireHelper.Answers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyAnswers {
    private List<Map<String, Object>> response_general = new ArrayList<>();
    private volatile static SurveyAnswers uniqueInstance;

    public SurveyAnswers() {
    }

    public void put_answer(String question, String answer, Integer serial) {
        Map<String, Object> map = new HashMap<>();
        map.put("question", question);
        map.put("answer", answer);
        map.put("serial", serial);
        response_general.add(map);
    }

    public void put_answer(String question, List<String> answers, Integer serial) {
        Map<String, Object> map = new HashMap<>();
        map.put("question", question);
        map.put("answer", answers);
        map.put("serial", serial);
        response_general.add(map);
    }

    public List<Map<String, Object>> getResponse_general() {
        return response_general;
    }

    public static SurveyAnswers getInstance() {
        if (uniqueInstance == null) {
            synchronized (Answers.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new SurveyAnswers();
                }
            }
        }
        return uniqueInstance;
    }
    public void clearData() {
        this.response_general = null;
    }

    @Override
    public String toString() {
        return String.valueOf(response_general);
    }

    public void setResponse_general(List<Map<String, Object>> response_general) {
        this.response_general = response_general;
    }
}
