package com.team2.slind.common.advice;

import com.team2.slind.common.exception.*;
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

    @ExceptionHandler({DuplicateMemberIdException.class})
    public ResponseEntity handleDuplicateMemberIdException(Exception e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler({DuplicateNicknameException.class})
    public ResponseEntity handleDuplicateNicknameException(Exception e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler({InvalidMemberIdLengthException.class})
    public ResponseEntity handleInvalidMemberIdLengthException(Exception e) {return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler({InvalidNicknameLengthException.class})
    public ResponseEntity handleInvalidNicknameLengthException(Exception e) {return ResponseEntity.status(400).body(e.getMessage());}
}
