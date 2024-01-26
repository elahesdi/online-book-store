package com.TOSAN.onlineBookStore.exception;

public class UserInfoNullException extends CustomErrorException {

    public UserInfoNullException() {
        super("username and password can not be null");
    }
}
