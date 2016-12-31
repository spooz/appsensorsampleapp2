package com.bartoszbalukiewicz.security;

import com.bartoszbalukiewicz.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * Created by Bartek on 18.09.2016.
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private Boolean isAdmin = Boolean.FALSE;
    private String notificationPhone;

    public CurrentUser(User user) {

        super(user.getEmail(), user.getPassword(), AuthorityUtils.NO_AUTHORITIES);
        this.isAdmin = user.getAdmin();
        this.notificationPhone = user.getNotificationsPhone();
    }

    public CurrentUser(User user, Collection<? extends GrantedAuthority> auths) {
        super(user.getEmail(), user.getPassword(), auths);
        this.isAdmin = user.getAdmin();
        this.notificationPhone = notificationPhone;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    public String notificationPhone() {
        return this.notificationPhone;
    }
}
