package com.team2.slind.board.vo;

import com.team2.slind.common.basevo.BaseVO;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @ToString
public class Board extends BaseVO {
    private Long boardPk;
    private Long memberPk;
    private String title;

}
