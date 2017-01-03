package com.gmail.jackkobec.internetshop.validation;

import java.util.regex.Pattern;

/**
 * LoginFormValidation
 *
 * @author Jack
 * @email jackkobec@gmail.com
 * @skype skypejs77
 */
public class LoginFormValidation implements Validator {

    public static final String LOGIN_REGEX_PATTERN = "^[A-Za-z0-9_]{3,15}$";
    public static final String PASSWORD_REGEX_PATTERN = "^[A-Za-z0-9_]{3,15}$";
    public static final String EMAIL_REGEX_PATTERN = "[a-zA-Z\\d@_\\-\\.]+?@([a-zA-Z]+\\.){1,2}[a-zA-Z]{2,3}";

    public boolean loginValidator(String login) {
        if (login.isEmpty() || login.equals(null)) {
            return false;
        }
        //chars A-Za-z0-9_  length 3-15 spaces denied
        Pattern rule = Pattern.compile(LOGIN_REGEX_PATTERN);
        return rule.matcher(login).matches();
    }

    public boolean passwordValidator(String password) {
        if (password.isEmpty() || password.equals(null)) {
            return false;
        }
        //chars A-Za-z0-9_  length 3-15 spaces denied
        Pattern rule = Pattern.compile(PASSWORD_REGEX_PATTERN);
        return rule.matcher(password).matches();
    }

    public boolean emailValidator(String email) {
        if (email.isEmpty() || email.equals(null)) {
            return false;
        }
        Pattern rule = Pattern.compile(EMAIL_REGEX_PATTERN);
        return rule.matcher(email).matches();
    }

}
