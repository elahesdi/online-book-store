package com.TOSAN.onlineBookStore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AuthResponseDto extends ResponseDto {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String jwt;

    public AuthResponseDto(String message, String jwt) {
        super(message);
        this.jwt = jwt;
    }


    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
