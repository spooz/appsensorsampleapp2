package com.bartoszbalukiewicz.security;

import com.bartoszbalukiewicz.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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
}
