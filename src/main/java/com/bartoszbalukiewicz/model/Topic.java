package com.bartoszbalukiewicz.model;

import javax.persistence.*;
import java.time.DateTimeException;
import java.util.Date;
import java.util.List;

/**
 * Created by postgres on 2016-09-20.
 */
@Entity
@Table(name="topic")
@SequenceGenerator(name = "seq_topic", sequenceName = "seq_topic")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_topic")
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String author;

    @OneToMany(mappedBy = "topic")
    private List<Message> messages;

    @Column
    private Date created = new Date();

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
