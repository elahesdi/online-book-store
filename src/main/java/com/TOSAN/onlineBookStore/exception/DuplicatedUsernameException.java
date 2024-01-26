package com.TOSAN.onlineBookStore.exception;

public class DuplicatedUsernameException extends CustomErrorException {

    public DuplicatedUsernameException(String username){
        super("This username currently is used: " + username);
    }
}
