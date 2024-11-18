package com.team2.slind.member.service;

import com.team2.slind.member.mapper.QuestionMapper;
import com.team2.slind.member.vo.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionMapper questionMapper;

    public ResponseEntity<List<Question>> getAllQuestion() {
        List<Question> list =  questionMapper.findAll();
        return ResponseEntity.ok().body(list);
    }
}
