package com.bartoszbalukiewicz.config;

import com.bartoszbalukiewicz.appsensor.event.events.request.AppSensorDetectionPointRE4Event;
import com.bartoszbalukiewicz.appsensor.event.publisher.AppSensorDetectionPointEventPublisher;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by postgres on 2016-10-07.
 */

@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger logger = Logger.getLogger(ExceptionHandlerAdvice.class);

    @Autowired
    private AppSensorDetectionPointEventPublisher eventPublisher;

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public void anyException(Exception exception) {
        exception.printStackTrace();
       logger.debug(exception);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public void MethodNotSupportedException(Exception exception) {
        eventPublisher.publishDetectionPointEvent(new AppSensorDetectionPointRE4Event());
        logger.error(exception);
    }

}
