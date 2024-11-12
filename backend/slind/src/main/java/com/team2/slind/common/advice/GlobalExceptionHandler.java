package com.team2.slind.common.advice;

import com.team2.slind.common.exception.BoardNotFoundException;
import com.team2.slind.common.exception.DuplicateTitleException;
import com.team2.slind.common.exception.UnauthorizedException;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //400 Error
    @ExceptionHandler({DuplicateTitleException.class})
    public ResponseEntity handleDuplicateTitleException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    //404 Error
    @ExceptionHandler({BoardNotFoundException.class})
    public ResponseEntity handleNotFoundException(Exception e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    //401 Error
    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity handleUnauthorizedException(Exception e) {
        return ResponseEntity.status(401).body(e.getMessage());
    }
}
