package com.bartoszbalukiewicz.appsensor;

import org.apache.log4j.Logger;
import org.owasp.appsensor.core.Response;
import org.owasp.appsensor.core.response.ResponseHandler;
/**
 * Created by postgres on 2016-09-26.
 */
public class NoopAppsensorResponseHandler implements ResponseHandler {


    Logger logger = org.apache.log4j.Logger.getLogger(NoopAppsensorResponseHandler.class);
    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(Response response) {
        logger.warn("GOT RESPONSE" + response.getAction());
    }

}
