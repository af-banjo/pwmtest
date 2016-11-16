package com.koodu.models;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author Abiola.Adebanjo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private String message;
    private Error error;

    public Response() {
    }

    public Response(String message) {
        this.message = message;
    }

    public Response(Error error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
