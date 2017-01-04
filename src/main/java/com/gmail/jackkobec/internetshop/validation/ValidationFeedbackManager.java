package com.gmail.jackkobec.internetshop.validation;

import javax.servlet.http.HttpServletRequest;

/**
 * ValidationFeedbackManager class for create visual feedback in the front end after validation.
 * Created by Jack on 04.01.2017.
 */
public class ValidationFeedbackManager {

    private static ValidationFeedbackManager validationFeedbackManager;

    //has-success or has-error"
    private static final String EMAIL_VALIDATION_FEEDBACK = "emailValidationFeedback";
    //glyphicon-ok or glyphicon-remove
    private static final String EMAIL_GLYPHICON_TYPE = "emailGlyphiconType";
    //has-success or has-error"
    private static final String PASSWORD_VALIDATION_FEEDBACK = "passwordValidationFeedback";
    //glyphicon-ok or glyphicon-remove
    private static final String PASSWORD_GLYPHICON_TYPE = "passwordGlyphiconType";

    private static final String NOTIFICATION_CLASS = "notification";
    private static final String NOTIFICATION_MESSAGE = "notificationMessage";

    public final String EMAIL_AND_PASSWORD_IS_EMPTY = "EMAIL_AND_PASSWORD_IS_EMPTY";
    public final String EMAIL_OR_PASSWORD_IS_EMPTY = "EMAIL_OR_PASSWORD_IS_EMPTY";
    public final String ONLY_EMAIL = "ONLY_EMAIL";
    public final String ONLY_PASSWORD = "ONLY_PASSWORD";

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
    public void createFeedBack(HttpServletRequest request, boolean isEmailValid, boolean isPasswordValid, String feedbackFor) {

        request.setAttribute(EMAIL_VALIDATION_FEEDBACK, isEmailValid
                ? "has-success" : "has-error");
        request.setAttribute(EMAIL_GLYPHICON_TYPE, isEmailValid
                ? "glyphicon-ok" : "glyphicon-remove");

        request.setAttribute(PASSWORD_VALIDATION_FEEDBACK, isPasswordValid
                ? "has-success" : "has-error");
        request.setAttribute(PASSWORD_GLYPHICON_TYPE, isPasswordValid
                ? "glyphicon-ok" : "glyphicon-remove");

        request.setAttribute(NOTIFICATION_CLASS, "my-notification");

        switch (feedbackFor) {

            case "EMAIL_AND_PASSWORD_IS_EMPTY":
                request.setAttribute(NOTIFICATION_MESSAGE, "Email and password is empty!");
                break;
            case "EMAIL_OR_PASSWORD_IS_EMPTY":
                request.setAttribute(NOTIFICATION_MESSAGE, "Email or password is empty!");
                break;
            case "ONLY_EMAIL":
                request.setAttribute(NOTIFICATION_MESSAGE, "Email is incorrect! Enter correct email like a jack@gmail.com");
                break;
            case "ONLY_PASSWORD":
                request.setAttribute(NOTIFICATION_MESSAGE, "Password is incorrect! Use chars A-Za-z0-9_ length 3-15");
                break;
        }
    }
}
