package com.bartoszbalukiewicz.security.ip;

import com.bartoszbalukiewicz.security.SecurityUtils;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by b.balukiewicz on 18.11.2016.
 */
public class SecurityIpFilter extends OncePerRequestFilter {

    private BannedIpStore bannedIpStore;
    private AccessDeniedHandler accessDeniedHandler = new AccessDeniedHandlerImpl();

    public SecurityIpFilter(BannedIpStore bannedIpStore) {
        this.bannedIpStore = bannedIpStore;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String ipAddress = SecurityUtils.getIpAddress(request);

        if(ipAddress == null || bannedIpStore.isBanned(ipAddress)) {
            accessDeniedHandler.handle(request, response, new AccessDeniedException("Ip banned"));
            return;
        }

        filterChain.doFilter(request, response);
    }
}
