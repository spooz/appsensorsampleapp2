package com.bartoszbalukiewicz.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bartek on 18.09.2016.
 */

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", nullable = false, updatable=false)
    private Long id;

    @NotEmpty
    @Column(name="email", nullable = false, updatable = false)
    private String email;

    @NotEmpty
    @Column(name="password", nullable = false)
    private String password;

    @Column(name="is_admin")
    private Boolean isAdmin = Boolean.FALSE;

    @Column(name="is_enabled")
    private Boolean isEnabled = Boolean.TRUE;

    @NotEmpty
    @Column(name="register_country")
    private String registerCountry;

    @Column
    private String notificationsPhone;

    @Transient
    public void hashPassword() {
        if (this.password != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            this.password = passwordEncoder.encode(this.password);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public String getRegisterCountry() {
        return registerCountry;
    }

    public void setRegisterCountry(String registerCountry) {
        this.registerCountry = registerCountry;
    }

    public String getNotificationsPhone() {
        return notificationsPhone;
    }

    public void setNotificationsPhone(String notificationsPhone) {
        this.notificationsPhone = notificationsPhone;
    }
}
