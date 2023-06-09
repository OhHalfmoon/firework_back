package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.approval.ApprovalResponseDto;
import com.ohalfmoon.firework.dto.approval.ApprovalSaveDto;
import com.ohalfmoon.firework.dto.approval.ApprovalStorageDto;
import com.ohalfmoon.firework.dto.approval.ApprovalUpdateDto;
import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
    @RequiredArgsConstructor
    public class ApprovalService {
        private final ApprovalRepository approvalRepository;

        //임시저장
        @Transactional
        public Long storage(ApprovalSaveDto saveDto) {
            return approvalRepository.save(saveDto.toStorageApproval()).getApprovalNo();
        }

        //기안 제출(결재)
        @Transactional
        public Long register(ApprovalSaveDto saveDto) {
            return approvalRepository.save(saveDto.toSaveApproval()).getApprovalNo();
        }


        public ApprovalResponseDto get (final String approvalName) {
            ApprovalEntity approvalEntity = approvalRepository.findByApprovalName(approvalName);
            return new ApprovalResponseDto(approvalEntity);
        }

//        public List getMyList (final Long userNo) {
//            return approvalRepository.findAllByMemberEntity(MemberEntity.builder().userNo(userNo).build());
//        }

        //결재 서류 수정
        @Transactional
        public ApprovalResponseDto updateStorage(long approvalNo, ApprovalUpdateDto updateDto) {
            ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);

            approvalEntity.updateStorage(
                    updateDto.getApprovalName(),
                    updateDto.getLineNo(),
                    updateDto.getDocboxNo(),
                    updateDto.getApproContent(),
                    updateDto.isStorage(),
                    updateDto.getApprovalState());

            return approvalEntity.toDto();
        }

        //결재 서류 수정
//        @Transactional
//        public  ApprovalResponseDto updateState(long approvalNo, ApprovalUpdateDto updateDto) {
//            ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);
//            approvalEntity.updat
//            return approvalEntity.toDto();
//        }
}
