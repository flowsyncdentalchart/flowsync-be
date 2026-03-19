package com.flowsync.dto;

public class AuthResponse {
    private String access_token;
    private String expires_in;
    private String tokenType;
    private String message;


    public AuthResponse(String access_token, String expires_in, String tokenType, String message) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.tokenType = tokenType;
        this.message = message;

    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
