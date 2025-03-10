package com.eazybytes.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomeAlreadyExistException extends RuntimeException{

    public CustomeAlreadyExistException(String message) {
        super(message);
    }

}
