package com.koodu.models;

import org.springframework.data.annotation.Id;

/**
 *
 * @author Abiola.Adebanjo
 */
public class Bookmark {

    @Id
    private String Id;
    private String userId;
    private String url;
    private String savedDate;
    private String readDate;

    public Bookmark() {
    }

    public Bookmark(String userId, String url, String savedDate, String readDate) {
        this.userId = userId;
        this.url = url;
        this.savedDate = savedDate;
        this.readDate = readDate;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(String savedDate) {
        this.savedDate = savedDate;
    }

    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }
}
