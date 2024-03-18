package org.jwt.commons.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class CommonException extends RuntimeException {
    private HttpStatus status;
    private Map<String, List<String>> messages;

    //여러필드에 대한 어려개 메세지를 받아 생성
    public CommonException(Map<String, List<String>> messages, HttpStatus status) {
        super();
        this.status = status;
        this.messages = messages;
    }

    //단일필드에 대한 메세지를 받아 생성
    public CommonException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Map<String, List<String>> getMessages() {
        return messages;
    }
}
