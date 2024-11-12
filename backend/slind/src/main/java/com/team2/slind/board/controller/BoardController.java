package com.team2.slind.board.controller;

import com.team2.slind.board.dto.request.BoardCreateRequest;
import com.team2.slind.board.dto.response.BoardResponse;
import com.team2.slind.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity createBoard(@RequestBody BoardCreateRequest boardCreateRequest) {
        return boardService.createBoard(boardCreateRequest);

    }

    @GetMapping("/check")
    public ResponseEntity checkDuplicateTitle(@RequestParam("title") String title) {
        return boardService.checkDuplicateBoard(title);
    }

    @DeleteMapping("/{boardPk}")
    public ResponseEntity deleteBoard(@PathVariable("boardPk") Long boardPk) {
        return boardService.deleteBoard(boardPk);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponse>> getBoardList() {
        return boardService.getBoardList();
    }





}
