package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.ApprovalResponseDto;
import com.ohalfmoon.firework.dto.ApprovalSaveDto;
import com.ohalfmoon.firework.dto.ApprovalStorageDto;
import com.ohalfmoon.firework.dto.ApprovalUpdateDto;
import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

    @Service
    @RequiredArgsConstructor
    public class ApprovalService {
        private final ApprovalRepository approvalRepository;

//        public ApprovalEntity register1(ApprovalEntity approvalEntity) {
//            return approvalRepository.save(approvalEntity);
//        }

//        public ApprovalEntity get (final String approvalName) {
//            return approvalRepository.findByApprovalName(approvalName);
//        }
        @Transactional
        public Long register(ApprovalSaveDto saveDto) {
            return approvalRepository.save(saveDto.toApproval()).getApprovalNo();
        }


        public ApprovalResponseDto get (final String approvalName) {
            ApprovalEntity approvalEntity = approvalRepository.findByApprovalName(approvalName);
            return new ApprovalResponseDto(approvalEntity);
        }

//        public List getMyList (final Long userNo) {
//            return approvalRepository.findAllByMemberEntity(MemberEntity.builder().userNo(userNo).build());
//        }


        @Transactional
        public ApprovalResponseDto updateStorage(long approvalNo, ApprovalStorageDto storageDto) {
            ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);

            approvalEntity.updateStorage(storageDto.storage);

            return approvalEntity.toDto();
        }

        @Transactional
        public  ApprovalResponseDto updateState(long approvalNo, ApprovalUpdateDto updateDto) {
            ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);
            approvalEntity.updateState(updateDto.approvalState);
            return approvalEntity.toDto();
        }
}
