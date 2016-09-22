package com.bartoszbalukiewicz.security;

import com.bartoszbalukiewicz.model.Role;
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

    public static Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getTypeAsString()));
        }

        return authorities;
    }

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

}
