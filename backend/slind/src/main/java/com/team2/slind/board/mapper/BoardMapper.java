package com.team2.slind.board.mapper;

import com.team2.slind.board.vo.Board;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BoardMapper {

    void addBoard(Board board);
    int findCountByBoardTitle(String boardTitle);
    Long deleteByBoardPk(Long boardPk);
    Optional<Board> findByBoardPk(Long boardPk);
    int findCountByBoardPk(Long boardPk);
    List<Board> findAllBoards();
    Optional<LocalDateTime> findRecentCreatedDate(Long memberPk);
}
