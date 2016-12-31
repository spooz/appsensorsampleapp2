package com.bartoszbalukiewicz.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Bartek on 06.12.2016.
 */
@Entity
@SequenceGenerator(name = "seq_notification", sequenceName = "seq_notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_notification")
    private Long id;

    private Date time = new Date();

    private String source;

    private String ipAddress;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
