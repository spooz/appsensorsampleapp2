package com.bartoszbalukiewicz.appsensor;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.owasp.appsensor.core.Response;
import org.owasp.appsensor.event.RestEventManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Bartek on 06.11.2016.
 */
@Component
public class ResponseChecker {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestEventManager restEventManager;

    @Autowired
    private NoopResponseHandler noopResponseHandler;

    private DateTime lastCheck = new DateTime();
    DateTimeFormatter fmt = ISODateTimeFormat.dateTime();

    @Scheduled(fixedDelay = 5000)
    public void checkResponses() {
        Collection<Response> responses = restEventManager.getResponses(fmt.print(lastCheck));
        logger.debug(responses.toString() + " found responses: " + responses.size());
        for(Response response : responses) {
            noopResponseHandler.handle(response);
        }
        lastCheck = DateTime.now();

    }
}
