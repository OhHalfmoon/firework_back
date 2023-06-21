package com.ohalfmoon.firework.dto.paging;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * packageName    : com.ohalfmoon.firework.dto.paging
 * fileName       : PageInfo
 * author         : banghansol
 * date           : 2023/06/12
 * description    : 페이지 정보 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/12        banghansol       최초 생성
 */
@Getter
@ToString
@Builder
public class PageInfo {
    // 페이지 번호
    private int pageNum;

    // 이 페이지가 현재 페이지인지 여부
    private boolean isCurrent;

    // 페이지네이션별 쿼리스트링 정보
    private String queryString;
}
