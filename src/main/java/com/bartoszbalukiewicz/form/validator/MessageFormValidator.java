package com.bartoszbalukiewicz.form.validator;

import com.bartoszbalukiewicz.appsensor.event.AppSensorDetectionPointIE1Event;
import com.bartoszbalukiewicz.appsensor.event.publisher.AppSensorDetectionPointEventPublisher;
import com.bartoszbalukiewicz.form.MessageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Bartek on 08.11.2016.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MessageFormValidator implements Validator {

    @Autowired
    private AppSensorDetectionPointEventPublisher eventPublisher;

    @Override
    public boolean supports(Class<?> aClass) {
        return MessageForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        MessageForm form = (MessageForm) o;
        validateXSS(form, errors);

    }

    private void validateXSS(MessageForm form, Errors errors) {
        if(ValidationUtils.isHtml(form.getMessage()) || ValidationUtils.isHtml(form.getTitle())) {
            errors.reject("XSS Atempt");
            eventPublisher.publishDetectionPointEvent(new AppSensorDetectionPointIE1Event());
        }
    }
}
