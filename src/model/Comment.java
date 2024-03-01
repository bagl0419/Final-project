package model;

import java.time.LocalDateTime;

public class Comment {
    private Long id;
    private String value;
    private Blog blog;
    private User author;
    private LocalDateTime createdAt;

    public Comment(Long id, String value, Blog blog, User author, LocalDateTime createdAt) {
        this.id = id;
        this.value = value;
        this.blog = blog;
        this.author = author;
        this.createdAt = createdAt;
    }

    public Comment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
