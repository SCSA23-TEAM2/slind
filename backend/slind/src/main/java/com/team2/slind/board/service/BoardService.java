package com.team2.slind.board.service;

import com.team2.slind.board.dto.request.BoardCreateRequest;
import com.team2.slind.board.dto.request.BookmarkUpdateRequest;
import com.team2.slind.board.dto.response.BoardResponse;
import com.team2.slind.board.mapper.BoardMapper;
import com.team2.slind.board.mapper.BookmarkMapper;
import com.team2.slind.board.vo.Board;
import com.team2.slind.common.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service @Slf4j
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;
    private final BookmarkMapper bookmarkMapper;
    private final Logger logger = LoggerFactory.getLogger(BoardService.class);
    public ResponseEntity<Void> createBoard(BoardCreateRequest boardCreateRequest, Long memberPk) {
        logger.info("Start createBoard in BoardService");
        //멤버버의 이번달 게시판 생성 여부 체크
        Optional<LocalDateTime> lastCreatedOpt = boardMapper.findRecentCreatedDate(memberPk);

        if (lastCreatedOpt.isPresent()){
            YearMonth lastCreatedMonth = YearMonth.from(lastCreatedOpt.get());
            YearMonth currentMonth = YearMonth.now();

            if (lastCreatedMonth.equals(currentMonth)) {
                throw new LastMonthCreationException(LastMonthCreationException.LAST_MONTH_CREATION);
            }
        }

        String title = boardCreateRequest.getTitle();
        logger.info("memberPk:{}",memberPk);
        logger.info("title:{}",title);
        if (checkDuplicate(title)) {
            throw new DuplicateTitleException(DuplicateTitleException.DUPLICATE_BOARD_TITLE);
        }


        Board board = Board.builder().title(title).memberPk(memberPk).build();
        boardMapper.addBoard(board);
        logger.info("Finish CreateBoard in BoardService");
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> checkDuplicateBoard(String title) {
        if (checkDuplicate(title)){
            throw new DuplicateTitleException(DuplicateTitleException.DUPLICATE_BOARD_TITLE);
        }
        return ResponseEntity.ok().build();
    }

    public boolean checkDuplicate(String title) {
        int result = boardMapper.findCountByBoardTitle(title);
        logger.info("checkDuplicate result: {}", result==1);
        return result==1;
    }

    public ResponseEntity<Void> deleteBoard(Long boardPk, Long memberPk) {
        Board board = boardMapper.findByBoardPk(boardPk).orElseThrow(()->
                new BoardNotFoundException(BoardNotFoundException.BOARD_NOT_FOUND));
        if (!board.getMemberPk().equals(memberPk)){
            throw new UnauthorizedException(UnauthorizedException.UNAUTHORIZED_DELETE_BOARD);
        }
        Long result = boardMapper.deleteByBoardPk(boardPk);
        if (result == 0L){
            throw new AlreadyDeletedException(AlreadyDeletedException.ALREADY_DELETED_BOARD);
        }
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<BoardResponse>> getBoardList(){
        List<BoardResponse> responseList = boardMapper.findAllBoards().stream().map(board ->
                BoardResponse.builder().boardPk(board.getBoardPk()).boardTitle(board.getTitle()).build())
                .toList();

        return ResponseEntity.ok().body(responseList);
    }

    public ResponseEntity<List<BoardResponse>> getBookmarkList(Long memberPk) {
//        List<Board> bookmarkList = bookmarkMapper.findByMemberPk(memberPk);
        List<Board> bookmarkList = boardMapper.findListByBookmarkByMemberPk(memberPk);

        List<BoardResponse> responseList = bookmarkList.stream().map(board -> BoardResponse.builder()
                .boardPk(board.getBoardPk()).boardTitle(board.getTitle()).build()).toList();
        return ResponseEntity.ok().body(responseList);
    }

}
