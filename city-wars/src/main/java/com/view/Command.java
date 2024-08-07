package com.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Command {

    SIGNUP_PAGE("signup"),
    SIGNUP("user create -u (?<username>.*) -p (?<password>.*) (?<passwordConfirmation>.*) -email (?<email>.*) -n (?<nickname>.*)"),
    SIGNUP_RANDOM("user create -u (?<username>.*) -p random -email (?<email>.*) -n (?<nickname>.*)"),
    ADMIN_LOGIN("login admin (?<password>.*)"),
    LOGIN("user login -u (?<username>.*) -p (?<password>.*)"),
    FORGOT_PASSWORD("forgot password"),
    EXIT("exit"),
    ;

    private final String regex;

    Command(String regex) {
        this.regex = regex;
    }

    public Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches())
            return matcher;
        return null;
    }
}
