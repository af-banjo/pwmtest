package com.koodu.exception;

import com.koodu.models.Response;

/**
 *
 * @author Abiola.Adebanjo
 */
public class TransactionException extends Exception {

    Response response;

    public TransactionException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.response = new Response(errorCode, errorMessage);
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
