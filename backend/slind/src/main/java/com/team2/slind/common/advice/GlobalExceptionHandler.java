package com.team2.slind.common.advice;

import com.team2.slind.common.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField())
                    .append(" : ")
                    .append(fieldError.getRejectedValue())
                    .append("(이)가 ")// 필드 이름
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
    @ExceptionHandler({UnauthorizedException.class,
            LastMonthCreationException.class})
    public ResponseEntity<String> handleUnauthorizedException(Exception e) {
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
