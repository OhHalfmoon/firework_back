package com.ohalfmoon.firework.model.spec;

import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.AttachEntity;
import com.ohalfmoon.firework.model.AttachEntity_;
import com.ohalfmoon.firework.model.BoardEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

/**
 * packageName    : com.ohalfmoon.firework.model.spec
 * fileName       : AttachSpec
 * author         : banghansol
 * date           : 2023/06/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/19        banghansol       최초 생성
 */
public class AttachSpec {
    public static Specification<AttachEntity> approvalNo(final Long approvalNo) {
        return (root, query, criteriaBuilder) -> {
            if(approvalNo == null) return null;

            Join<AttachEntity, ApprovalEntity> attach = root.join(AttachEntity_.APPROVAL_ENTITY, JoinType.INNER);

            return criteriaBuilder.equal(attach.get("approvalNo"), approvalNo);
        };
    }

    public static Specification<AttachEntity> boardNo(final Long boardNo) {
        return (root, query, criteriaBuilder) -> {
            if(boardNo == null) return null;

            Join<AttachEntity, BoardEntity> attach = root.join(AttachEntity_.boardEntity, JoinType.INNER);

            return criteriaBuilder.equal(attach.get("boardNo"), boardNo);
        };
    }
}
