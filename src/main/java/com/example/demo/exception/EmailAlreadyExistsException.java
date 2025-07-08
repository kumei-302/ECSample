package com.example.demo.exception;

/**
 * メールアドレス重複例外
 * 作成者　粂井
 */
public class EmailAlreadyExistsException extends Exception {
    
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
    
    public EmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}