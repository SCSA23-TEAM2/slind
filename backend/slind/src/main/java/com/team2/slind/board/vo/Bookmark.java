package com.team2.slind.board.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Bookmark {
    private Long bookmarkPk;
    private Long memberPk;
    private Long boardPk;
}
