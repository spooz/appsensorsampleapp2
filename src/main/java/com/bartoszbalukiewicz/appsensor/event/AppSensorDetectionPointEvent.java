package com.bartoszbalukiewicz.appsensor.event;

import org.owasp.appsensor.core.DetectionPoint;
import org.owasp.appsensor.core.User;

/**
 * Created by Bartek on 10.10.2016.
 */
public abstract class AppSensorDetectionPointEvent {

    protected User appSensorUser;

    public abstract DetectionPoint getDetectionPoint();

    public  User getAppSensorUser() {
        return this.appSensorUser;
    }

    public void setAppSensorUser(User appSensorUser) {
        this.appSensorUser = appSensorUser;
    }

}
