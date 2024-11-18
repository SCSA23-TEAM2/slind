package com.team2.slind.common.advice;

import com.team2.slind.common.exception.*;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()) // 필드 이름
                    .append(" : ")
                    .append(fieldError.getDefaultMessage()) // 유효성 검사 실패 메시지
                    .append("\n");
        }

        return ResponseEntity.badRequest().body(sb.toString());
    }
    //400 Error
    @ExceptionHandler({
            DuplicateTitleException.class,
            AlreadyDeletedException.class,
            ContentException.class,
            NoReactionExcetpion.class,
            AlreadyReactedException.class,
            NoReactionExcetpion.class,
            JudgementNotFoundException.class,
            DuplicateMemberIdException.class,
            DuplicateNicknameException.class,
            InvalidNicknameLengthException.class,
            InvalidMemberIdLengthException.class
    })
    public ResponseEntity<String> handleDuplicateTitleException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    //404 Error
    @ExceptionHandler({BoardNotFoundException.class, ArticleNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(Exception e) {
        return ResponseEntity.status(404).body(e.getMessage());
    }

    //401 Error
    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<String> handleUnauthorizedException(Exception e) {
        return ResponseEntity.status(401).body(e.getMessage());
    }
}
