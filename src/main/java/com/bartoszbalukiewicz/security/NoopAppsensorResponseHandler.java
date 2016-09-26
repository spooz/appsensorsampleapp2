package com.bartoszbalukiewicz.security;

import org.owasp.appsensor.core.Response;
import org.owasp.appsensor.core.response.ResponseHandler;

/**
 * Created by postgres on 2016-09-26.
 */
public class NoopAppsensorResponseHandler implements ResponseHandler {


    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(Response response) {
        //
    }

}
