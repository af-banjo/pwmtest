package com.koodu.models;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author Abiola.Adebanjo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private String responseCode;
    private String responseDecription;
    private String providerToken;

    public Response(String responseCode, String responseDecription) {
        this.responseCode = responseCode;
        this.responseDecription = responseDecription;
    }

    public Response(String providerToken) {
        this.providerToken = providerToken;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDecription() {
        return responseDecription;
    }

    public void setResponseDecription(String responseDecription) {
        this.responseDecription = responseDecription;
    }

    public String getProviderToken() {
        return providerToken;
    }

    public void setProviderToken(String providerToken) {
        this.providerToken = providerToken;
    }

}
