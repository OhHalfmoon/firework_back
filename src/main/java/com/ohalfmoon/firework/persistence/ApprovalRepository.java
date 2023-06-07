package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprovalRepository extends JpaRepository<ApprovalEntity, Long> {
    ApprovalEntity findByApprovalName(String approvalName);

    List<ApprovalEntity> findAllByMemberEntity(MemberEntity memberEntity);


}
