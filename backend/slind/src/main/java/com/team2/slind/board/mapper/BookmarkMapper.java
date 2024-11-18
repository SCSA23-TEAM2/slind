package com.team2.slind.board.mapper;

import com.team2.slind.board.vo.Board;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookmarkMapper {
//    List<Board> findByMemberPk(Long memberPk);

    void deleteOriginalList(Long memberPk);
    void insertBookmark(Map<String ,Object> map);

}
