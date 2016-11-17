package com.koodu.utils;

import org.springframework.http.HttpStatus;

/**
 *
 * @author Abiola.Adebanjo
 */
public class Utils {

    public static HttpStatus getHttpStatusFromResponseCode(String code) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        switch (code) {
            case Constants.DUPLICATE_ERROR_CODE:
                httpStatus = HttpStatus.CONFLICT;
                break;

        }
        return httpStatus;
    }
}
