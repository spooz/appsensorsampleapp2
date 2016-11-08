package com.bartoszbalukiewicz.form.validator;

import org.springframework.web.util.HtmlUtils;

/**
 * Created by Bartek on 08.11.2016.
 */
public class ValidationUtils {

    public static boolean isHtml(String input) {
        boolean isHtml = false;
        if (input != null) {
            if (!input.equals(HtmlUtils.htmlEscape(input))) {
                isHtml = true;
            }
        }
        return isHtml;
    }
}
