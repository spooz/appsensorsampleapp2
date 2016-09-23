package com.bartoszbalukiewicz.model;

import javax.persistence.*;

/**
 * Created by postgres on 2016-09-20.
 */
@Entity
@Table(name="message")
@SequenceGenerator(name = "seq_message", sequenceName = "seq_message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_message")
    private Long id;

    @Column
    private String title;

    @Column
    private String message;

    @Column
    private String author;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
