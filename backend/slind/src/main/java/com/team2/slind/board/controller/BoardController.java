package com.team2.slind.board.controller;

import com.team2.slind.board.dto.request.BoardCreateRequest;
import com.team2.slind.board.dto.request.BookmarkUpdateRequest;
import com.team2.slind.board.dto.response.BoardResponse;
import com.team2.slind.board.service.BoardService;
import com.team2.slind.board.service.BookmarkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    static Long memberPk = 1L;
    private final BookmarkService bookmarkService;

    @PostMapping("/auth")
    public ResponseEntity<Void> createBoard(@RequestBody @Valid BoardCreateRequest boardCreateRequest) {
        return boardService.createBoard(boardCreateRequest, memberPk);

    }

    @GetMapping("/check")
    public ResponseEntity<Void> checkDuplicateTitle(@RequestParam("title") String title) {
        return boardService.checkDuplicateBoard(title);
    }

    @DeleteMapping("/auth/{boardPk}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("boardPk") Long boardPk) {
        return boardService.deleteBoard(boardPk, memberPk);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponse>> getBoardList() {
        return boardService.getBoardList();
    }

    @GetMapping("/auth/favorite")
    public ResponseEntity<List<BoardResponse>> getBookmarkList(){
        return boardService.getBookmarkList(memberPk);

    }

    @PostMapping("/auth/favorite")
    public ResponseEntity<Void> updateBookmarkList(@RequestBody BookmarkUpdateRequest bookmarkUpdateRequest){
        return bookmarkService.updateBookmarkList(bookmarkUpdateRequest, memberPk);
    }




}
