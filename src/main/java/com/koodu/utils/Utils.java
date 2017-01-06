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
            case Constants.SUBSCRIBER_NOT_FOUND_ERROR_CODE:
                httpStatus = HttpStatus.NOT_FOUND;
                break;
            case Constants.SECURITY_VIOLATION_ERROR_CODE:
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            case Constants.INSUFFICIENT_BALANCE_ERROR_CODE:
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
        }
        return httpStatus;
    }
}
