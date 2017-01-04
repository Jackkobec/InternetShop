package com.gmail.jackkobec.internetshop.validation;

import java.util.regex.Pattern;

/**
 * <p>InputDataValidation class. For validate login and register data with regexp from front end.
 * Created by Jack on 30.12.2016.
 */
public class InputDataValidation implements Validation {

    private static InputDataValidation inputDataValidation;

    private static final String LOGIN_REGEX_PATTERN = "^[A-Za-z0-9_]{3,15}$";
    private static final String PASSWORD_REGEX_PATTERN = "^[A-Za-z0-9_]{3,15}$";
    private static final String EMAIL_REGEX_PATTERN = "[a-zA-Z\\d@_\\-\\.]+?@([a-zA-Z]+\\.){1,2}[a-zA-Z]{2,3}";

    private InputDataValidation() {
    }

    /**
     * @return InputDataValidation instance.
     */
    public static Validation getInputDataValidation() {

        return (inputDataValidation == null)
                ? inputDataValidation = new InputDataValidation()
                : inputDataValidation;
    }

    /**
     * Method for login validation.
     *
     * @param login
     * @return is login valid.
     */
    public boolean loginValidator(String login) {

        if (login.isEmpty() || login.equals(null)) {
            return false;
        }
        //chars A-Za-z0-9_  length 3-15 spaces denied
        Pattern rule = Pattern.compile(LOGIN_REGEX_PATTERN);

        return rule.matcher(login).matches();
    }

    /**
     * Method for password validation.
     *
     * @param password
     * @return is password valid.
     */
    public boolean passwordValidator(String password) {

        if (password.isEmpty() || password.equals(null)) {
            return false;
        }
        //chars A-Za-z0-9_  length 3-15 spaces denied
        Pattern rule = Pattern.compile(PASSWORD_REGEX_PATTERN);

        return rule.matcher(password).matches();
    }

    /**
     * Method for email validation.
     *
     * @param email
     * @return is email valid.
     */
    public boolean emailValidator(String email) {

        if (email.isEmpty() || email.equals(null)) {
            return false;
        }
        Pattern rule = Pattern.compile(EMAIL_REGEX_PATTERN);

        return rule.matcher(email).matches();
    }
}
