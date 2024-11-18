package com.team2.slind.member.mapper;

import com.team2.slind.member.vo.Question;

import java.util.List;

public interface QuestionMapper {
    String findTextByPk(Long questionPk);
    List<Question> findAll();
}
