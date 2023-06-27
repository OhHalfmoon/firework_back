package com.ohalfmoon.firework.model.spec;

import com.ohalfmoon.firework.model.BoardEntity;
import com.ohalfmoon.firework.model.BoardEntity_;
import org.springframework.data.jpa.domain.Specification;

/**
 * packageName    : com.ohalfmoon.firework.model.spec
 * fileName       : BoardSpec
 * author         : 이지윤
 * date           : 2023/06/26
 * description    : 게시판 검색
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/26        이지윤            최초 생성
 */

public class BoardSpec {
    public static Specification<BoardEntity> boardSearch(String type, String keyword) {
        if(keyword == null) return null;

        switch (type) {
            case "T" : return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get(BoardEntity_.BOARD_TITLE), "%"+keyword+"%"));

            case "C" : return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get(BoardEntity_.BOARD_CONTENT), "%"+keyword+"%"));

            case "TC" : return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get(BoardEntity_.BOARD_TITLE), "%"+keyword+"%"),
                            criteriaBuilder.like(root.get(BoardEntity_.BOARD_CONTENT), "%"+keyword+"%")
                    ));

            default: return null;
        }
    }

}
