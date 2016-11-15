package com.bartoszbalukiewicz.appsensor.security.context;

import com.bartoszbalukiewicz.appsensor.security.response.InMemoryUserResponseCache;
import com.bartoszbalukiewicz.appsensor.security.response.UserResponseCache;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppSensorSecurityContextRepository implements SecurityContextRepository {

    private final UserResponseCache appSensor;

    private final SecurityContextRepository delegate;

    public AppSensorSecurityContextRepository() {
        this(new InMemoryUserResponseCache(), new HttpSessionSecurityContextRepository());
    }

    public AppSensorSecurityContextRepository(UserResponseCache appSensor,
                                              SecurityContextRepository delegate) {
        this.appSensor = appSensor;
        this.delegate = delegate;
    }

    @Override
    public SecurityContext loadContext(HttpRequestResponseHolder requestResponseHolder) {
        SecurityContext context = delegate.loadContext(requestResponseHolder);

        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return context;
        }

        String principal = authentication.getName();

        // check logout
        if (appSensor.isUserLoggedOut(principal)) {

            if(appSensor.isLogoutProcessed(principal)) {

                // logout's been processed, reset
                appSensor.clearUserLoggedOut(principal);
            } else {

                // set logout as processing on this request, so next request get's reset
                appSensor.processLogout(principal);
            }

            // return an empty request context - this is the actual logout
            return SecurityContextHolder.createEmptyContext();
        }

        return context;
    }

    @Override
    public void saveContext(SecurityContext context, HttpServletRequest request, HttpServletResponse response) {
        delegate.saveContext(context, request, response);
    }

    @Override
    public boolean containsContext(HttpServletRequest request) {
        return delegate.containsContext(request);
    }

}