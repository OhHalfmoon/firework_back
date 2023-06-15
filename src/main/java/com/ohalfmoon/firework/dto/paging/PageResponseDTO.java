package com.ohalfmoon.firework.dto.paging;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
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
@ToString
public class PageResponseDTO <Entity> {

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

    // 이전 페이지 묶음 여부
    private boolean hasPrevBlock;

    // 다음 페이지 묶음 여부
    private boolean hasNextBlock;

    // 이전 페이지 번호
    private int prevPageNum;

    // 다음 페이지 번호
    private int nextPageNum;

    // 이전 페이지 블록 이동 번호
    private int prevBlockNum;

    // 다음 페이지 블록 이동 번호
    private int nextBlockNum;

    // 페이지 번호 리스트(페이지 번호, 현재 페이지인지 여부)
    private List<PageInfo> pageInfoList;


    public PageResponseDTO(Page<Entity> entityPage) {

        totalPage = entityPage.getTotalPages();

        Pageable pageable = entityPage.getPageable();

        // Pageable 의 page 번호가 0부터 시작함
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();

        // 페이지 목록 계산용 임시 변수
        int temp = pageable.getPageNumber() / pageable.getPageSize() * pageable.getPageSize();

        startPageNum = temp + 1;

        endPageNum = Math.min(totalPage, temp + pageable.getPageSize());

        prev = page > 1;
        next = page < totalPage;

        List<Integer> pageList = IntStream.rangeClosed(startPageNum, endPageNum)
                .boxed().collect(Collectors.toList());

         pageInfoList = pageList.stream()
                .map(page -> PageInfo.builder()
                            .pageNum(page)
                            .isCurrent(page == this.page)
                            .build()
                ).collect(Collectors.toList());

        prevPageNum = page - 1;
        nextPageNum = page + 1;

        hasPrevBlock = startPageNum > 1;
        hasNextBlock = endPageNum + 1 <= totalPage;

        prevBlockNum = hasPrevBlock ? startPageNum - 1 : startPageNum;
        nextBlockNum = hasNextBlock ? endPageNum + 1 : totalPage;
    }

}
