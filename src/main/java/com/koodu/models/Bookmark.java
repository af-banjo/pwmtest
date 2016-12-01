package com.koodu.models;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
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
    @CreatedDate
    private LocalDateTime createdAt;
    private LocalDateTime readAt;

    public Bookmark() {
    }

    public Bookmark(String userId, String url, LocalDateTime readAt) {
        this.userId = userId;
        this.url = url;
        this.readAt = readAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getReadAt() {
        return readAt;
    }

    public void setReadAt(LocalDateTime readAt) {
        this.readAt = readAt;
    }

}
