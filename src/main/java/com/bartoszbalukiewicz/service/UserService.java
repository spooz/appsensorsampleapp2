package com.bartoszbalukiewicz.service;

import com.bartoszbalukiewicz.appsensor.geolocation.CustomGeoLocator;
import com.bartoszbalukiewicz.form.RegisterForm;
import com.bartoszbalukiewicz.model.User;
import com.bartoszbalukiewicz.repository.UserRepository;
import com.maxmind.geoip2.record.Country;
import org.owasp.appsensor.core.geolocation.GeoLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by Bartek on 18.09.2016.
 */
@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private CustomGeoLocator geoLocator;

    @Autowired
    public UserService(UserRepository userRepository, CustomGeoLocator geoLocator)  {
        this.userRepository = userRepository;
        this.geoLocator = geoLocator;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public User registerUser(RegisterForm form, String ipAddress) {
        User user = new User();
        user.setEmail(form.getEmail());

        Country country = geoLocator.getCountry(ipAddress);
        user.setRegisterCountry(country == null ? "" : country.getIsoCode());

        user.setPassword(form.getPassword());
        user.hashPassword();
        userRepository.save(user);
        return user;
    }
}
