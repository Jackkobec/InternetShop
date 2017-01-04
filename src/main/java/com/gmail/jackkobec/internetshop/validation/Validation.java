package com.gmail.jackkobec.internetshop.validation;

/**
 * Validation interface.
 *
 * @author Jack
 * @email jackkobec@gmail.com
 * @skype skypejs77
 */
public interface Validation {

    boolean loginValidator(String login);

    boolean passwordValidator(String password);

    boolean emailValidator(String email);

}
