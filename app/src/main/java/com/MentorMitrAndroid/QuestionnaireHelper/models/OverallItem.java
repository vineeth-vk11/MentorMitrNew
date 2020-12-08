package com.MentorMitrAndroid.QuestionnaireHelper.models;

public class OverallItem {
    String activity;
    int engagement;
    int energy;

    public OverallItem(String activity) {
        this.activity = activity;
        this.engagement = 0;
        this.energy = 0;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public int getEngagement() {
        return engagement;
    }

    public void setEngagement(int engagement) {
        this.engagement = engagement;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
