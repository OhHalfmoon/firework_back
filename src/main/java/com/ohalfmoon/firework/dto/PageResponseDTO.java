package com.ohalfmoon.firework.dto;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : PageResponseDTO
 * author         : banghansol
 * date           : 2023/06/12
 * description    : 페이징 조회용 공통 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/12        banghansol       최초 생성
 */
@Getter
public class PageResponseDTO <Entity, Dto> {
    // 조회한 DTO 리스트
    private List<Dto> dtoList;

    // 총 페이지 번호
    private int totalPage;

    // 현재 페이지 번호
    private int page;

    // 목록 사이즈
    private int size;

    // 페이지 묶음 시작번호
    private int startPageNum;

    // 페이지 묶음 시작번호
    private int endPageNum;

    // 이전 페이지 존재여부
    private boolean prev;

    // 다음 페이지 존재여부
    private boolean next;

    // 페이지 번호 리스트
    private List<Integer> pageNumList;

    public PageResponseDTO(Page<Entity> entityPage, Function<Entity, Dto> fn) {
        dtoList = entityPage.stream().map(fn).collect(Collectors.toList());

        totalPage = entityPage.getTotalPages();

        Pageable pageable = entityPage.getPageable();

        // Pageable 의 page 번호가 0부터 시작함
        this.page = pageable.getPageNumber();
        this.size = pageable.getPageSize();

        int temp = pageable.getPageNumber() % 10;

        startPageNum = temp;

        endPageNum = Math.min(totalPage, temp + pageable.getPageSize() - 1);

        prev = startPageNum > 1;
        next = page < totalPage;

        pageNumList = IntStream.rangeClosed(startPageNum, endPageNum)
                .boxed().collect(Collectors.toList());
    }
}
