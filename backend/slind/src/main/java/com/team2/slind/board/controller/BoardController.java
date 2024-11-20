package com.team2.slind.board.controller;

import com.team2.slind.board.dto.request.BoardCreateRequest;
import com.team2.slind.board.dto.request.BookmarkUpdateRequest;
import com.team2.slind.board.dto.response.BoardResponse;
import com.team2.slind.board.service.BoardService;
import com.team2.slind.board.service.BookmarkService;
import com.team2.slind.member.login.service.CustomMemberDetails;
import com.team2.slind.security.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BookmarkService bookmarkService;

    @PostMapping("/auth")
    public ResponseEntity<Void> createBoard(@RequestBody @Valid BoardCreateRequest boardCreateRequest) {
        return boardService.createBoard(boardCreateRequest, SecurityUtil.getMemberPk());

    }

    @GetMapping("/check")
    public ResponseEntity<Void> checkDuplicateTitle(@RequestParam("title") String title) {
        return boardService.checkDuplicateBoard(title);
    }

    @DeleteMapping("/auth/{boardPk}")
    public ResponseEntity<Void> deleteBoard(@PathVariable("boardPk") Long boardPk) {
        Long memberPk = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication == null || !(authentication.getPrincipal() instanceof CustomMemberDetails))) {
            CustomMemberDetails memberDetails = (CustomMemberDetails) authentication.getPrincipal();
            memberPk = memberDetails.getMemberPk();
        }
        return boardService.deleteBoard(boardPk, memberPk);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponse>> getBoardList() {
        return boardService.getBoardList();
    }

    @GetMapping("/auth/favorite")
    public ResponseEntity<List<BoardResponse>> getBookmarkList(){
        return boardService.getBookmarkList(SecurityUtil.getMemberPk());

    }

    @PostMapping("/auth/favorite")
    public ResponseEntity<Void> updateBookmarkList(@RequestBody BookmarkUpdateRequest bookmarkUpdateRequest){
        return bookmarkService.updateBookmarkList(bookmarkUpdateRequest, SecurityUtil.getMemberPk());
    }




}
