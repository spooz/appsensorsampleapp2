package com.bartoszbalukiewicz.security;

import com.bartoszbalukiewicz.model.User;
import com.bartoszbalukiewicz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by Bartek on 18.09.2016.
 */
@Service
public class CurrentUserDetailsService implements UserDetailsService{

    private UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User  user = userService.findByEmail(email);
        if(user == null)
            throw new UsernameNotFoundException("User not found");

        return new CurrentUser(user);
    }
}
