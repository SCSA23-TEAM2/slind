package com.team2.slind.member.controller;

import com.team2.slind.member.service.QuestionService;
import com.team2.slind.member.vo.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping
    public ResponseEntity<List<Question>> getQuestion() {
        return questionService.getAllQuestion();

    }
}
