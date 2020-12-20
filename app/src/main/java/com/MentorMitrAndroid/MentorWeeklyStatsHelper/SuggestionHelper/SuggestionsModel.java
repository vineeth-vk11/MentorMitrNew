package com.MentorMitrAndroid.MentorWeeklyStatsHelper.SuggestionHelper;

import java.io.Serializable;

public class SuggestionsModel implements Serializable {

    String activityName, hours;

    public SuggestionsModel() {
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
