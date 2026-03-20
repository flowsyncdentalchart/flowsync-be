package com.flowsync.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"accessToken", "expiresIn",  "tokenType", "message" })
public class AuthResponse {
    private String accessToken;
    private String expiresIn;
    private String tokenType;
    private String message;

    public AuthResponse(String access_token, String expires_in, String tokenType, String message) {
        this.accessToken = access_token;
        this.expiresIn = expires_in;
        this.tokenType = tokenType;
        this.message = message;

    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
