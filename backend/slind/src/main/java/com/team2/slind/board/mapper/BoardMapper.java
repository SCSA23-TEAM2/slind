package com.team2.slind.board.mapper;

import com.team2.slind.board.vo.Board;

import java.util.Optional;

public interface BoardMapper {

    void addBoard(Board board);
    Optional<Board> findByBoardTitle(String boardTitle);
    Long deleteByBoardPk(Long boardPk);
}
