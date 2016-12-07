package com.bartoszbalukiewicz.model.view;

import java.util.Date;

/**
 * Created by b.balukiewicz on 07.12.2016.
 */
public class NotificationView {

    public NotificationView(Date time, String source, String ipAddress) {
        this.time = time;
        this.source = source;
        this.ipAddress = ipAddress;
    }

    private Date time;
    private String source;
    private String ipAddress;

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
