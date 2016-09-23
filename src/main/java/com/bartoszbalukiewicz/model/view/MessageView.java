package com.bartoszbalukiewicz.model.view;

/**
 * Created by postgres on 2016-09-23.
 */
public class MessageView {

    public MessageView(Long id, String title, String message, String author) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.author = author;
    }

    private Long id;
    private String title;
    private String message;
    private String author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
