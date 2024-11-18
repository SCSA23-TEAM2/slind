package com.team2.slind.board.service;

import com.team2.slind.board.dto.request.BookmarkUpdateRequest;
import com.team2.slind.board.mapper.BookmarkMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookmarkService {
    private final Logger logger = LoggerFactory.getLogger(BookmarkService.class);
    private final BookmarkMapper bookmarkMapper;

    @Transactional
    public void deleteOriginalBookmarks(Long memberPk) {
        bookmarkMapper.deleteOriginalList(memberPk);
    }

    @Transactional
    public void insertNewBookmarks(Long memberPk, List<Long> boardPkList) {
        if (boardPkList != null && !boardPkList.isEmpty()) {
            logger.info("boardPkList:{}", boardPkList);
//            bookmarkMapper.insertBookmark(memberPk, boardPkList);
            logger.info("boardPkList: {}", boardPkList);

            // MyBatis에 전달할 파라미터 맵 생성
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("memberPk", memberPk);
            paramMap.put("boardPkList", boardPkList);

            bookmarkMapper.insertBookmark(paramMap);

        }
    }

    public ResponseEntity<Void> updateBookmarkList(BookmarkUpdateRequest bookmarkUpdateRequest, Long memberPk) {
        //기존 북마크 전체 삭제
        deleteOriginalBookmarks(memberPk);

        //새로운 리스트 insert
        insertNewBookmarks(memberPk, bookmarkUpdateRequest.getBoardPkList());

        return ResponseEntity.ok().build();
    }
}
