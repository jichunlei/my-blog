package com.jicl.exception;

/**
 * 业务异常
 *
 * @author : xianzilei
 * @date : 2020/3/31 15:40
 */
public class BusinessException extends Exception{
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
