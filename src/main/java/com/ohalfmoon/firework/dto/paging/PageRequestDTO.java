package com.ohalfmoon.firework.dto.paging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : PageRequestDTO
 * author         : banghansol
 * date           : 2023/06/12
 * description    : 페이징 검색용 공통 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/12        banghansol       최초 생성
 */
@Getter
@Setter
public class PageRequestDTO {
    private int page;
    private int size;

    private String type;
    private String keyword;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    public PageRequestDTO(int page, int size){
        this.page = page;
        this.size = size;
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page-1, size, sort);
    }
}
