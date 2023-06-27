package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.AttachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * packageName    : com.ohalfmoon.firework.persistence
 * fileName       : AttachRepository
 * author         : banghansol
 * date           : 2023/06/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/19        banghansol       최초 생성
 */
public interface AttachRepository extends JpaRepository<AttachEntity, Long>, JpaSpecificationExecutor<AttachEntity> {
    Long deleteAttachEntitiesByApprovalEntity_ApprovalNo(Long approvalNo);

    List<AttachEntity> findAllByApprovalEntity(ApprovalEntity approvalEntity);

}
