package com.bartoszbalukiewicz.config;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by postgres on 2016-10-07.
 */

@ControllerAdvice
public class ExceptionControllerAdvice {

    private static final Logger logger = Logger.getLogger(ExceptionControllerAdvice.class);

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public void anyException(Exception exception) {
       logger.debug(exception.getClass() + exception.getMessage());
    }
}
