package com.bartoszbalukiewicz.security;

import com.bartoszbalukiewicz.appsensor.event.events.auth.AppSensorDetectionPointAE1Event;
import com.bartoszbalukiewicz.appsensor.event.events.auth.AppSensorDetectionPointAE2Event;
import com.bartoszbalukiewicz.appsensor.event.publisher.AppSensorDetectionPointEventPublisher;
import com.bartoszbalukiewicz.model.User;
import com.bartoszbalukiewicz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by Bartek on 13.11.2016.
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private BCryptPasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();

    @Autowired
    private AppSensorDetectionPointEventPublisher eventPublisher;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        User dbUser = userService.findByEmail(username);

        if(dbUser == null)
            throw new BadCredentialsException("1000");

        if(!passwordEncoder.matches(password, dbUser.getPassword())) {
            eventPublisher.publishDetectionPointEvent(new AppSensorDetectionPointAE2Event(),authentication);
            throw new BadCredentialsException("1000");
        }


        eventPublisher.publishDetectionPointEventWithSessionID(new AppSensorDetectionPointAE1Event(),authentication);

        return new UsernamePasswordAuthenticationToken(username, password, AuthorityUtils.NO_AUTHORITIES);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
