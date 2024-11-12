package com.team2.slind.board.service;

import com.team2.slind.board.dto.request.BoardCreateRequest;
import com.team2.slind.board.mapper.BoardMapper;
import com.team2.slind.board.vo.Board;
import com.team2.slind.common.exception.BoardNotFoundException;
import com.team2.slind.common.exception.DuplicateTitleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;
    static Long memberPk = 1L;

    public ResponseEntity createBoard(BoardCreateRequest boardCreateRequest) {
        String title = boardCreateRequest.getTitle();
        if (checkDuplicate(title)){
            throw new DuplicateTitleException(DuplicateTitleException.DUPLICATE_BOARD_TITLE);
        }
        Board board = Board.builder().title(title).memberPk(memberPk).build();
        boardMapper.addBoard(board);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity checkDuplicateBoard(String title) {
        if (checkDuplicate(title)){
            throw new DuplicateTitleException(DuplicateTitleException.DUPLICATE_BOARD_TITLE);
        }
        return ResponseEntity.ok().build();
    }

    public boolean checkDuplicate(String title) {
        Optional<Board> board = boardMapper.findByBoardTitle(title);
        return board.isPresent();
    }

    public ResponseEntity deleteBoard(Long boardPk) {
        Long result = boardMapper.deleteByBoardPk(boardPk);
        if (result == 0L){
            throw new BoardNotFoundException(BoardNotFoundException.BOARD_NOT_FOUND);
        }
        return ResponseEntity.ok().build();

    }
}
