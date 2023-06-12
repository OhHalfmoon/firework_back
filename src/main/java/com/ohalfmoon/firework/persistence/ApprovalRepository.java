package com.ohalfmoon.firework.persistence;



import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * packageName    : com.ohalfmoon.firework.persistence
 * fileName       : ApprovalRepository
 * author         : 오상현
 * date           : 2023/06/07
 * description    : 결재 Repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        오상현            최초 생성
 */
public interface ApprovalRepository extends JpaRepository<ApprovalEntity, Long> {
    ApprovalEntity findByApprovalName(String approvalName);

    List<ApprovalEntity> findAllByMemberEntity(MemberEntity memberEntity);

    ApprovalEntity findByApprovalNo(Long approvalNo);

}
