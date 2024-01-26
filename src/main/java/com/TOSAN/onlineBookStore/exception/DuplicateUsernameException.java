package com.TOSAN.onlineBookStore.exception;

public class DuplicateUsernameException extends CustomErrorException {

    public DuplicateUsernameException(String username){
        super("This username is currently in use: " + username);
    }
}
