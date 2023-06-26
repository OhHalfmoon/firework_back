package com.ohalfmoon.firework.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : JsTreeDto
 * author         : 방한솔
 * date           : 2023/06/23
 * description    : JsTree 용 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/23        방한솔           최초 생성
 */
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsTreeNodeDto<T> {
    // 노드 id
    private String id;
    // 노드 text
    private String text;
    // 노드 icon
    private String icon;

    // 노드 상태
    private JsTreeState state;

    // 노드 parent id
    private String parent;

    // 노드 실제 데이터
    private T data;

    // 자식 소유여부
    private boolean children;
    // li_attr     : {}  // attributes for the generated LI node
    // a_attr      : {}  // attributes for the generated A node

    @Builder
    public JsTreeNodeDto(String id, String text, String icon, JsTreeState state, String parent, T data, boolean children) {
        this.id = id;
        this.text = text;
        this.icon = icon;
        this.state = state;
        this.parent = parent;
        this.data = data;
        this.children = children;
    }
}
