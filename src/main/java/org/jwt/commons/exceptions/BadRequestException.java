package org.jwt.commons.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class BadRequestException extends CommonException {

    //복수개의 필드에서 잘못된요청이 들어왔을때 예외를 생성
    public BadRequestException(Map<String, List<String>> messages) {
        super(messages, HttpStatus.BAD_REQUEST);
    }

    //단일 필드에서 잘못된 요청이 들어왔을때 예외를 생성
    public BadRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
