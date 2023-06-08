package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApprovalService {
    private final ApprovalRepository approvalRepository;

    public ApprovalEntity register(ApprovalEntity approvalEntity) {
        return approvalRepository.save(approvalEntity);
    }

    public ApprovalEntity get (final String approvalName) {
        return approvalRepository.findByApprovalName(approvalName);
    }

    public List getMyList (final Long userNo) {
        return approvalRepository.findAllByMemberEntity(MemberEntity.builder().userNo(userNo).build());
    }
}
