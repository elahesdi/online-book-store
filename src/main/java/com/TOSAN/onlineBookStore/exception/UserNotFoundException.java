package com.TOSAN.onlineBookStore.exception;

public class UserNotFoundException extends CustomErrorException {
    public UserNotFoundException(String userName){
        super("User not found: " + userName);
    }
}
