package com.ohalfmoon.firework.model.spec;

import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.AttachEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

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

            Join<AttachEntity, ApprovalEntity> attach = root.join("approvalEntity", JoinType.INNER);

            return criteriaBuilder.equal(attach.get("approvalNo"), approvalNo);
        };
    }
}
