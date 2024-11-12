package com.team2.slind.common.basevo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class BaseVO {
    private LocalDateTime createdDttm;
    private LocalDateTime deletedDttm;
    private Integer isDeleted;

    // 기본 생성자에서 초기 값 설정
    public BaseVO() {
        this.createdDttm = LocalDateTime.now();
        this.isDeleted = 0;
    }

}
