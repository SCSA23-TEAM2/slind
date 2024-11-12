package com.team2.slind.board.controller;

import com.team2.slind.board.dto.request.BoardCreateRequest;
import com.team2.slind.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity createBoard(@RequestBody BoardCreateRequest boardCreateRequest) {
        return boardService.createBoard(boardCreateRequest);

    }

    @GetMapping
    public ResponseEntity checkDuplicateTitle(@RequestParam("title") String title) {
        return boardService.checkDuplicateBoard(title);
    }

    @DeleteMapping("/{boardPk}")
    public ResponseEntity deleteBoard(@PathVariable("boardPk") Long boardPk) {
        return boardService.deleteBoard(boardPk);
    }




}
