package com.bartoszbalukiewicz.appsensor;

import com.bartoszbalukiewicz.appsensor.security.response.SpringSecurityUserManager;
import org.owasp.appsensor.core.Response;
import org.owasp.appsensor.core.User;
import org.owasp.appsensor.core.response.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Named;

/**
 * Created by Bartek on 26.09.2016.
 */
@Component
public class RestResponseHandler implements ResponseHandler {

    private String DISABLE_IP = "disableIp";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SpringSecurityUserManager springSecurityUserManager;

    @Override
    public void handle(Response response) {
        String action = response.getAction();
        User user = response.getUser();

        if(action.equals(LOG)) {
            logger.info("Response for user: " + user.getUsername());
        }

        if(action.equals(LOGOUT)) {
            springSecurityUserManager.logout(user);
            logger.info("Logging out user:" + user.getUsername());
        }

        if(action.equals(DISABLE_USER)) {
            springSecurityUserManager.disable(user);
            logger.info("Disabling user: " + user.getUsername());
        }


    }

}