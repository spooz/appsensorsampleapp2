package com.bartoszbalukiewicz.form.validator;

import com.bartoszbalukiewicz.form.RegisterForm;
import com.bartoszbalukiewicz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by postgres on 2016-10-07.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class RegisterFormValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegisterForm form = (RegisterForm) o;
        validateUnique(form, errors);
    }

    private void validateUnique(RegisterForm form, Errors errors) {
        if(userService.findByEmail(form.getEmail()) != null) {
            errors.reject("User already exists");
        }
    }


}
