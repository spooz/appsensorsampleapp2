package com.bartoszbalukiewicz.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by postgres on 2016-09-19.
 */
public class SecurityUtils {

    public static CurrentUser getAuthenticatedCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null)
            return null;

        if(auth instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return (CurrentUser) auth.getPrincipal();
    }

    public static String getAuthenticatedUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth == null ? "" : auth.getName();
    }

    public static Authentication getAuthentiaction() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
