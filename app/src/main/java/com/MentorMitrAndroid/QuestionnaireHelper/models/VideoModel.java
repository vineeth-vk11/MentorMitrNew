package com.MentorMitrAndroid.QuestionnaireHelper.models;

import java.util.Date;

public class VideoModel {
    String creator;
    Date date;
    String content;
    String link;

    public VideoModel() {
        this.creator = "NA";
        this.date = new Date();
        this.content = "NA";
        this.link = "NA";
    }

    public VideoModel(String creator, Date date, String content, String link) {

        this.creator = creator;
        this.date = date;
        this.content = content;
        this.link = link;
    }


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
