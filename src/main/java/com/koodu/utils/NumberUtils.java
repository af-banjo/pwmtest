package com.koodu.utils;

/**
 *
 * @author Abiola.Adebanjo
 */
public class NumberUtils {

    public static boolean isEmpty(int value) {
        String strValue = String.valueOf(value);
        return strValue == null || strValue.trim().equals("") || strValue.trim().isEmpty();
    }
}
