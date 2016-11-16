package com.koodu.utils;

/**
 *
 * @author Abiola.Adebanjo
 */
public class StringUtils {

    public static boolean isEmpty(String value) {
        return value == null || value.trim().equals("") || value.trim().isEmpty();
    }
}
