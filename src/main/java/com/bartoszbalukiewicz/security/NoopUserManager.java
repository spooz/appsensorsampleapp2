package com.bartoszbalukiewicz.security;

import org.owasp.appsensor.core.User;
import org.owasp.appsensor.core.logging.Loggable;
import org.owasp.appsensor.core.response.UserManager;
import org.slf4j.Logger;

import javax.inject.Named;

/**
 * Created by postgres on 2016-09-26.
 */
@Named
@Loggable
public class NoopUserManager implements UserManager {

    private Logger logger;

    /**
     * {@inheritDoc}
     */
    @Override
    public void logout(User user) {
        logger.info("The no-op user manager did not logout the user as requested.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void disable(User user) {
        logger.info("The no-op user manager did not disable the user as requested.");
    }

}
