package com.koodu.utils;

/**
 *
 * @author Abiola.Adebanjo
 */
public class StringUtils {

    public static boolean isEmpty(String value) {
        return value == null || value.trim().equals("") || value.trim().isEmpty();
    }

    public static boolean isEmpty(long value) {
        String temp = String.valueOf(value);
        return temp == null || temp.trim().equals("") || temp.trim().isEmpty();
    }
}
