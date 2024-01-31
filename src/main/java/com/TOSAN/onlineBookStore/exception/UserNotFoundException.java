package com.TOSAN.onlineBookStore.exception;

public class UserNotFoundException extends EntityNotFound {
    public UserNotFoundException(String userName){
        super("User not found: " + userName);
    }
}
