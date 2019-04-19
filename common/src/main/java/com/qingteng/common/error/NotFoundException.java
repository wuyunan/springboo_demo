package com.qingteng.common.error;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super("Book id not found : " + id);
    }

}