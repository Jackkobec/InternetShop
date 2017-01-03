package com.gmail.jackkobec.internetshop.validation;

/**
 * Validator
 *
 * @author Jack
 * @email jackkobec@gmail.com
 * @skype skypejs77
 */
public interface Validator {

    boolean loginValidator(String login);

    boolean passwordValidator(String password);

    boolean emailValidator(String email);

}
