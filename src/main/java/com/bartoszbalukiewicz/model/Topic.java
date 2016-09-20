package com.bartoszbalukiewicz.model;

import javax.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "topic")
    private List<Message> messages;

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
