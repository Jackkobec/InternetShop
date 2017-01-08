package com.gmail.jackkobec.internetshop.service;

/**
 * Created by Jack on 07.01.2017.
 */
public class LanguageService {

    private static final String ENGLISH = "en_EN";
    private static final String RUSSIAN = "ru_RU";


    public String getLanguageBySelect(Integer selection) {

        switch (selection) {
            case 0:
                return ENGLISH;
            case 1:
                return RUSSIAN;
            default:
                return ENGLISH;
        }
    }
}
