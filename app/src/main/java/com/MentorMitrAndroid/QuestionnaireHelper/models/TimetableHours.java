package com.MentorMitrAndroid.QuestionnaireHelper.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TimetableHours {
    List<Map<String, String>> survey;

    public TimetableHours() {
        this.survey = new ArrayList<>();
    }
    public void add(Map<String, String> subject) {
        this.survey.add(subject);
    }

    public TimetableHours(Context context, List<Map<String, String>> survey) {
        this.survey = survey;
    }

    public List<Map<String, String>> getSurvey() {
        return survey;
    }

    public void setSurvey(List<Map<String, String>> survey) {
        this.survey = survey;
    }
}