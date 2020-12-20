package com.MentorMitrAndroid.GradesHelper.GradesHelper;

public class GradeModel {

    String marksScored, totalMarks, dateSelected;
    Double percentage;

    public GradeModel() {
    }

    public String getMarksScored() {
        return marksScored;
    }

    public void setMarksScored(String marksScored) {
        this.marksScored = marksScored;
    }

    public String getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(String totalMarks) {
        this.totalMarks = totalMarks;
    }

    public String getDateSelected() {
        return dateSelected;
    }

    public void setDateSelected(String dateSelected) {
        this.dateSelected = dateSelected;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
