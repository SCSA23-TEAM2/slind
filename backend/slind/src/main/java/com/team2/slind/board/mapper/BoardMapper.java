package com.team2.slind.board.mapper;

import com.team2.slind.board.vo.Board;
import org.apache.ibatis.annotations.Param;

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

    List<Board> findListByMemberPkFirst(@Param("memberPk") Long memberPk, @Param("size") int size);

    List<Board> findListByMemberPk(@Param("memberPk") Long memberPk,
                                   @Param("lastPk") Long lastPk,
                                   @Param("size") int size);

}
