package com.MentorMitrAndroid.QuestionnaireHelper.models;

/**
 * Created by mbt on 4/13/16.
 */
public class StoreItems {
    String serialNumber;
    String college;
    String course;
    String selection;
    String achieve;
    String improve;


    public StoreItems()
    {

    }

    public StoreItems(String serialNumber, String college, String course, String selection, String achieve, String improve)
    {
        this.serialNumber = serialNumber;
        this.college = college;
        this.course = course;
        this.selection = selection;
        this.achieve =achieve;
        this.improve = improve;
    }

    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    public void setCollege(String college)
    {
        this.college = college;
    }

    public void setCourse(String course)
    {
        this.course = course;
    }

    public void setSelection(String selection)
    {
        this.selection = selection;
    }
    public void setAchieve(String achieve)
    {
        this.achieve = achieve;
    }
    public void setImprove(String improve)
    {
        this.improve = improve;
    }


    public String getSerialNumber()
    {

        return serialNumber;
    }

    public String getCollege()
    {
        return college;
    }

    public String getCourse()
    {
        return course;
    }

    public String getSelection()
    {
        return selection;
    }

    public String getAchieve()
    {
        return achieve;
    }

    public String getImprove()
    {
        return improve;
    }

/*
    public void setStatus(String status) {
        this.status = status;
    }


    public String getStatus() {
        return status;
    }*/
}
