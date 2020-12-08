package com.MentorMitrAndroid.QuestionnaireHelper.models;

import java.util.Arrays;
import java.util.List;

public class TimetableAnswer {
    List<String> sun = Arrays.asList(new String[24]);
    List<String> mon = Arrays.asList(new String[24]);
    List<String> tue = Arrays.asList(new String[24]);
    List<String> wed = Arrays.asList(new String[24]);
    List<String> thu = Arrays.asList(new String[24]);
    List<String> fri = Arrays.asList(new String[24]);
    List<String> sat = Arrays.asList(new String[24]);

    public List<String> getSun() {
        return sun;
    }

    public void setSun(List<String> sun) {
        this.sun = sun;
    }

    public List<String> getMon() {
        return mon;
    }

    public void setMon(List<String> mon) {
        this.mon = mon;
    }

    public List<String> getTue() {
        return tue;
    }

    public void setTue(List<String> tue) {
        this.tue = tue;
    }

    public List<String> getWed() {
        return wed;
    }

    public void setWed(List<String> wed) {
        this.wed = wed;
    }

    public List<String> getThu() {
        return thu;
    }

    public void setThu(List<String> thu) {
        this.thu = thu;
    }

    public List<String> getFri() {
        return fri;
    }

    public void setFri(List<String> fri) {
        this.fri = fri;
    }

    public List<String> getSat() {
        return sat;
    }

    public void setSat(List<String> sat) {
        this.sat = sat;
    }
}
