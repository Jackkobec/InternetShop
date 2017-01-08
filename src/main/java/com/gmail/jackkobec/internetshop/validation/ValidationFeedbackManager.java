package com.gmail.jackkobec.internetshop.validation;

import javax.servlet.http.HttpServletRequest;

/**
 * ValidationFeedbackManager class for create visual feedback in the front end after validation.
 * Created by Jack on 04.01.2017.
 */
public class ValidationFeedbackManager {

    private static ValidationFeedbackManager validationFeedbackManager;
    private Validation validation = InputDataValidation.getInputDataValidation();

    //has-success or has-error"
    private static final String EMAIL_VALIDATION_FEEDBACK = "emailValidationFeedback";
    //glyphicon-ok or glyphicon-remove
    private static final String EMAIL_GLYPHICON_TYPE = "emailGlyphiconType";
    //has-success or has-error"
    private static final String PASSWORD_VALIDATION_FEEDBACK = "passwordValidationFeedback";
    //glyphicon-ok or glyphicon-remove
    private static final String PASSWORD_GLYPHICON_TYPE = "passwordGlyphiconType";
    //has-success or has-error"
    private static final String CONFIRMATION_PASSWORD_VALIDATION_FEEDBACK = "confirmationPasswordValidationFeedback";
    //glyphicon-ok or glyphicon-remove
    private static final String CONFIRMATION_PASSWORD_GLYPHICON_TYPE = "confirmationPasswordGlyphiconType";

    private static final String NOTIFICATION_CLASS = "notification";
    private static final String NOTIFICATION_MESSAGE = "notificationMessage";

    public final String EMAIL_AND_PASSWORD_IS_EMPTY = "EMAIL_AND_PASSWORD_IS_EMPTY";
    public final String EMAIL_OR_PASSWORD_IS_EMPTY = "EMAIL_OR_PASSWORD_IS_EMPTY";
    public final String ONLY_EMAIL = "ONLY_EMAIL";
    public final String ONLY_PASSWORD = "ONLY_PASSWORD";

    public final String PASSWORDS_NOT_EQUALS_CONFIRM_PASSWORD = "PASSWORDS_NOT_EQUALS_CONFIRM_PASSWORD";

    public final String USER_NOT_FOUND_OR_INCORRECT_DATA = "USER_NOT_FOUND_OR_INCORRECT_DATA";


    private ValidationFeedbackManager() {
    }

    /**
     * @return ValidationFeedbackManager instance.
     */
    public static ValidationFeedbackManager getValidationFeedbackManager() {

        return (validationFeedbackManager == null)
                ? validationFeedbackManager = new ValidationFeedbackManager()
                : validationFeedbackManager;
    }

    /**
     * Method for create visual feedback in the front end.
     *
     * @param request         HttpServletRequest
     * @param isEmailValid
     * @param isPasswordValid
     * @param feedbackFor     Means from what method was call. Need for correct messages in the front end.
     */
    public void createFeedBack(HttpServletRequest request, boolean isEmailValid, boolean isPasswordValid,
                               boolean isPasswordNotEqualsConfirmationPassword, String feedbackFor) {

        request.setAttribute(EMAIL_VALIDATION_FEEDBACK, isEmailValid
                ? "has-success" : "has-error");
        request.setAttribute(EMAIL_GLYPHICON_TYPE, isEmailValid
                ? "glyphicon-ok" : "glyphicon-remove");


        request.setAttribute(PASSWORD_VALIDATION_FEEDBACK, isPasswordValid
                ? "has-success" : "has-error");
        request.setAttribute(PASSWORD_GLYPHICON_TYPE, isPasswordValid
                ? "glyphicon-ok" : "glyphicon-remove");


        request.setAttribute(CONFIRMATION_PASSWORD_VALIDATION_FEEDBACK, isPasswordNotEqualsConfirmationPassword
                ? "has-success" : "has-error");
        request.setAttribute(CONFIRMATION_PASSWORD_GLYPHICON_TYPE, isPasswordNotEqualsConfirmationPassword
                ? "glyphicon-ok" : "glyphicon-remove");


        request.setAttribute(NOTIFICATION_CLASS, "my-notification");

        switch (feedbackFor) {

            case "EMAIL_AND_PASSWORD_IS_EMPTY":
                request.setAttribute(NOTIFICATION_MESSAGE, "validation.email_and_password_is_empty");
                break;
            case "EMAIL_OR_PASSWORD_IS_EMPTY":
                request.setAttribute(NOTIFICATION_MESSAGE, "validation.email_or_password_is_empty");
                break;
            case "ONLY_EMAIL":
                request.setAttribute(NOTIFICATION_MESSAGE, "validation.incorrect_email");
                break;
            case "ONLY_PASSWORD":
                request.setAttribute(NOTIFICATION_MESSAGE, "validation.incorrect_password");
                break;
            case "PASSWORDS_NOT_EQUALS_CONFIRM_PASSWORD":
                request.setAttribute(NOTIFICATION_MESSAGE, "validation.password_not_equals_confirmation_password");
                break;
            case "USER_NOT_FOUND_OR_INCORRECT_DATA":
                request.setAttribute(NOTIFICATION_MESSAGE, "validation.user_not_found_or_incorrect_data");
                break;
        }
    }

    public boolean preValidation(HttpServletRequest request, String email, String password, String passwordConfirmation) {

        boolean isEmailValid = validation.emailValidator(email);
        boolean isPasswordValid = validation.passwordValidator(password);
        boolean isPasswordEqualsConfirmationPassword = password.equals(passwordConfirmation);


        if (passwordConfirmation == null) {
            isPasswordEqualsConfirmationPassword = true;
        }


        if (email.isEmpty() && password.isEmpty()) {

            createFeedBack(request, isEmailValid, isPasswordValid, !isPasswordEqualsConfirmationPassword,
                    EMAIL_AND_PASSWORD_IS_EMPTY);

            return true;
        }

        if (email.isEmpty() || password.isEmpty()) {

            if ((!email.isEmpty()) && password.isEmpty())
                isPasswordEqualsConfirmationPassword = !isPasswordEqualsConfirmationPassword;

            createFeedBack(request, isEmailValid, isPasswordValid, isPasswordEqualsConfirmationPassword,
                    EMAIL_OR_PASSWORD_IS_EMPTY);

            return true;
        }

        if (!isEmailValid) {

            createFeedBack(request, isEmailValid, isPasswordValid, isPasswordEqualsConfirmationPassword,
                    ONLY_EMAIL);

            return true;
        }

        if (!isPasswordValid) {

            createFeedBack(request, isEmailValid, isPasswordValid, isPasswordEqualsConfirmationPassword,
                    ONLY_PASSWORD);

            return true;
        }

        if (!isPasswordEqualsConfirmationPassword) {

            createFeedBack(request, isEmailValid, isPasswordValid, isPasswordEqualsConfirmationPassword,
                    PASSWORDS_NOT_EQUALS_CONFIRM_PASSWORD);

            return true;
        }

        return false;
    }
}
