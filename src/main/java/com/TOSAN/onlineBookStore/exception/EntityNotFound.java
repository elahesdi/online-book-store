package com.TOSAN.onlineBookStore.exception;

public class EntityNotFound extends CustomErrorException {

    public EntityNotFound(String message){
        super(message);
    }

    public EntityNotFound(){}
}
