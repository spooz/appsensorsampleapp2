package com.bartoszbalukiewicz.security;

import com.bartoszbalukiewicz.appsensor.event.events.auth.AppSensorDetectionPointAE12Event;
import com.bartoszbalukiewicz.appsensor.event.events.auth.AppSensorDetectionPointAE1Event;
import com.bartoszbalukiewicz.appsensor.event.events.auth.AppSensorDetectionPointAE2Event;
import com.bartoszbalukiewicz.appsensor.event.publisher.AppSensorDetectionPointEventPublisher;
import com.bartoszbalukiewicz.model.User;
import com.bartoszbalukiewicz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        if(SecurityUtils.isCommonUserName(username)) {
            eventPublisher.publishDetectionPointEventWithSessionID(new AppSensorDetectionPointAE12Event(), authentication);
            throw new BadCredentialsException("1000");
        }

        String password = (String) authentication.getCredentials();

        User dbUser = userService.findByEmail(username);

        if(dbUser == null)
            throw new BadCredentialsException("1000");

        if(!dbUser.getEnabled())
            throw new DisabledException("1001");

        if(!passwordEncoder.matches(password, dbUser.getPassword())) {
            eventPublisher.publishDetectionPointEvent(new AppSensorDetectionPointAE2Event(),authentication);
            throw new BadCredentialsException("1000");
        }


        eventPublisher.publishDetectionPointEventWithSessionID(new AppSensorDetectionPointAE1Event(),authentication);

        return new UsernamePasswordAuthenticationToken(userDetailsService.loadUserByUsername(username), password, AuthorityUtils.NO_AUTHORITIES);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
