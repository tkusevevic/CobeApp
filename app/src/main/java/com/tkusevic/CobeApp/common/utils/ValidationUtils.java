package com.tkusevic.CobeApp.common.utils;

/**
 * Created by tkusevic on 23.01.2018..
 */

public class ValidationUtils {

    public static boolean isPriceValid(String input) {
        try {
            int tryy = Integer.parseInt(input);
            return true;
        } catch (Throwable throwable) {
            return false;
        }
    }

    public static boolean isSalaryValid(String input) {
        try {
            double tryy = Double.parseDouble(input);
            return true;
        } catch (Throwable throwable) {
            return false;
        }
    }

    public static boolean isEmailValid(String input) {
        return EmailCheckUtils.isValidEmail(input);
    }

    public static boolean isValid(String text) {
        return text != null && !text.trim().isEmpty();
    }
}
