package com.ohalfmoon.firework.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : JsTreeState
 * author         : banghansol
 * date           : 2023/06/23
 * description    : JsTree 상태 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/23        방한솔           최초 생성
 */
@Getter
@Setter
@ToString
public class JsTreeState {
    private boolean opened;
    private boolean disabled;
    private boolean selected;

    @Builder
    public JsTreeState(boolean opened, boolean disabled, boolean selected) {
        this.opened = opened;
        this.disabled = disabled;
        this.selected = selected;
    }
}
