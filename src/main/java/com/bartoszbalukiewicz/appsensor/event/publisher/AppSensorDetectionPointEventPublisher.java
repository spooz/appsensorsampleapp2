package com.bartoszbalukiewicz.appsensor.event.publisher;

import com.bartoszbalukiewicz.appsensor.event.AppSensorDetectionPointEvent;
import org.springframework.security.core.Authentication;

/**
 * Created by Bartek on 11.10.2016.
 */

public interface AppSensorDetectionPointEventPublisher {

    void publishDetectionPointEvent(AppSensorDetectionPointEvent event, Authentication authentication);
    void publishDetectionPointEvent(AppSensorDetectionPointEvent event);


}
