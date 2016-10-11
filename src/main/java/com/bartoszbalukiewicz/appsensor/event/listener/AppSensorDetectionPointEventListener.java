package com.bartoszbalukiewicz.appsensor.event.listener;

import com.bartoszbalukiewicz.appsensor.event.AppSensorDetectionPointEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * Created by Bartek on 11.10.2016.
 */
public interface AppSensorDetectionPointEventListener {

    @Async
    @EventListener
    void handleDetectionPointEvent(AppSensorDetectionPointEvent event);
}
