package com.TOSAN.onlineBookStore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AuthResponseDto {
    private String message;
    @JsonIgnore
    private String jwt;

    public AuthResponseDto(String message, String jwt) {
        this.message = message;
        this.jwt = jwt;
    }

    public AuthResponseDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
