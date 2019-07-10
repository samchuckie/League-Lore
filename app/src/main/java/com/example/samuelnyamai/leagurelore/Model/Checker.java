package com.example.samuelnyamai.leagurelore.Model;

import java.util.regex.Pattern;

import static com.example.samuelnyamai.leagurelore.Constants.ServerConstants.REGEX;

public class Checker {

    public static boolean checkEmail(String email) {
        // This method is called after checking EditText/Input is not empty
        // second part max of 4 dots
        boolean emailBool = false;
        if (!email.isEmpty()) {
            if (Pattern.matches(REGEX, email)) {
                emailBool = true;
            }
        }
        return emailBool;
    }

    public static boolean checkPassword(String password) {
        // This method is called after checking EditText/Input is not empty
        // implement check listener and display change icon accordinglt. Weak,strong,average,Really strong
        boolean checker = false;
        if (!password.trim().isEmpty()) {
            checker = true;
        }
        return checker;
    }

    public static boolean confirmPassword(String passwordEditText, String confirmPasswordEditText) {
        // This method is called after checking EditText/Input is not empty
        // TODO FIRST USE CHECKPASSWORD FOR WHEN ITS EMPTY
        boolean checker = false;
        if (checkPassword(passwordEditText) && checkPassword(confirmPasswordEditText)) {
            if (passwordEditText.equals(confirmPasswordEditText)) {
                checker = true;
            }
        }
        return checker;
    }
}
