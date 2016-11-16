package com.koodu.exception;

import com.koodu.models.Error;

/**
 *
 * @author Abiola.Adebanjo
 */
public class BookmarkException extends Exception {

    Error error;

    public BookmarkException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.error = new Error(errorCode, errorMessage);
    }

    public Error getError() {
        return error;
    }

}
