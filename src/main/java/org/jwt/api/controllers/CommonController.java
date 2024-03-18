package org.jwt.api.controllers;

import org.jwt.commons.exceptions.CommonException;
import org.jwt.commons.rests.JSONData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice("org.jwt.api.controllers")
public class CommonController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);


    @ExceptionHandler(Exception.class)
    //모든 Exception를 처리
    public ResponseEntity<JSONData> errorHandler(Exception e) {
        //기본값으로 500 설정
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        Object message = e.getMessage();




        if (e instanceof CommonException) {
            CommonException commonException = (CommonException) e;
            status = commonException.getStatus();

            if (commonException.getMessages() != null) message = commonException.getMessages();
        } else if (e instanceof BadCredentialsException) {
            status = HttpStatus.UNAUTHORIZED; //401
        }else if(e instanceof AccessDeniedException){
            status = HttpStatus.FORBIDDEN; //403
        }
        //BadCredentialsException -> 500 ->401
        //AccessDeniedException -> 500 ->403

        JSONData data = new JSONData();
        data.setSuccess(false);
        data.setStatus(status);
        data.setMessage(message);

        logger.error("error : {} ",e.getMessage(), e);

        return ResponseEntity.status(status).body(data);
    }
}
