package com.example.demo.exception;

/*
 * 作成者　粂井メールアドレス重複例外を定義するクラス 
 */
public class EmailAlreadyExistsException extends Exception {
    
    public EmailAlreadyExistsException(String message) {
        super(message);
    }
    
    public EmailAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}