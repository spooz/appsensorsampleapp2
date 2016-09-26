package com.bartoszbalukiewicz.appsensor;

import org.owasp.appsensor.core.Response;
import org.owasp.appsensor.core.response.ResponseHandler;

import javax.inject.Named;

/**
 * Created by Bartek on 26.09.2016.
 */
@Named
public class NoopResponseHandler implements ResponseHandler {

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(Response response) {
        //
    }

}