package com.team2.slind.board.mapper;

import com.team2.slind.board.vo.Board;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookmarkMapper {
    List<Board> findByMemberPk(Long memberPk);

    void deleteOriginalList(Long memberPk);
    void insertBookmark(@Param("memberPk") Long memberPk, @Param("boardPkList") List<Long> boardPkList);

}
