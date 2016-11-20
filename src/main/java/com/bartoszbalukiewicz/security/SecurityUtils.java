package com.bartoszbalukiewicz.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by postgres on 2016-09-19.
 */
public class SecurityUtils {

    private static Set<String> commonUserNames = new HashSet<>();

    static {
        commonUserNames.add("admin");
        commonUserNames.add("test");
        commonUserNames.add("administrator");
        commonUserNames.add("user");
    }

    public static boolean isCommonUserName(String username) {
        return commonUserNames.contains(username);
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

    public static Authentication getAuthentiaction() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }

}
