package com.demo.demoproject.dal.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("notice")
public class NoticeModel {
    @Id
    private String postID;
    private String title;
    private String description;
    private String date;

    
    public String getPostID() {
        return postID;
    }
    public void setPostID(String postID) {
        this.postID = postID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    
}
