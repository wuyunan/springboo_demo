package com.qingteng.common.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * TooManyException
 *
 * @author terry
 * @date 2019-01-29
 */
@ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
public class TooManyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public TooManyException() {
        super();
    }

    public TooManyException(String message) {
        super(message);
    }

    public TooManyException(String message, Throwable cause) {
        super(message, cause);
    }
}
