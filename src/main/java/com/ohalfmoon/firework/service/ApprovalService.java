package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.approval.*;
import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.SubLineEntity;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import com.ohalfmoon.firework.persistence.SubLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : ApprovalService
 * author         : 오상현
 * date           : 2023/06/08
 * description    : 결재 Service
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/08        오상현           최초 생성, save관련 (임시저장, 제출)
 * 2023/06/09        오상현           update관련 (문서수정, 임시저장->제출, 결재완료)
 * 2023/06/12        오상현           update 리턴 타입 변경 -> Long, getList 기능 추가
 */
@Service
@RequiredArgsConstructor
public class ApprovalService {
    private final ApprovalRepository approvalRepository;
    private final SubLineRepository subLineRepository;



    //기안 제출(결재)
    @Transactional
    public Long register(ApprovalSaveDto saveDto) {
        return approvalRepository.save(saveDto.toSaveApproval()).getApprovalNo();
    }

    //기안명을 통한 단일조회
    public ApprovalResponseDto getName (final String approvalName) {
        ApprovalEntity approvalEntity = approvalRepository.findByApprovalName(approvalName);
        return new ApprovalResponseDto(approvalEntity);
    }

    //기안번호를 통한 단일조회
    public ApprovalResponseDto get (final Long approvalNo) {
        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);
        return new ApprovalResponseDto(approvalEntity);
    }

    public List<ApprovalResponseDto> getMyList (final Long userNo) {
        return approvalRepository
                .findAllByMemberEntity(MemberEntity.builder().userNo(userNo).build())
                .stream().map(ApprovalResponseDto::new).collect(Collectors.toList());
    }

    public List<ApprovalLineDto> getApprovalUserName (final Long approvalNo) {

        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);
        MasterLineEntity masterLineEntity = approvalEntity.getMasterLineEntity();
        Long userNo = approvalEntity.getMemberEntity().getUserNo();
        String userName = masterLineEntity.getMemberEntity().getName();
        String userDept = masterLineEntity.getMemberEntity().getDeptEntity().getDeptName();
        String userPosition = masterLineEntity.getMemberEntity().getPositionEntity().getPositionName();

       return subLineRepository.findAllByMasterLineEntity_LineNo(masterLineEntity.getLineNo())
                .stream().map(ApprovalLineDto::new).collect(Collectors.toList());

    }

    //결재 서류 수정
    @Transactional
    public Long update(long approvalNo, ApprovalUpdateDto updateDto) {
        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);

        approvalEntity.update(
                updateDto.getApprovalName(),
                updateDto.getLineNo(),
                updateDto.getDocboxNo(),
                updateDto.getApproContent()
        );

        return approvalNo;
    }

    //기안 상태값을 변경
    @Transactional
    public  Long updateState(long approvalNo ,ApprovalStateDto stateDto) {
        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);
//        int approvalstate = approvalEntity.getApprovalState();
        approvalEntity.updateState(
                stateDto.getApprovalState()
        );

        return approvalNo;
    }

    //기안 삭제
    @Transactional
    public void delete(Long approvalNo) {
        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);
        approvalRepository.delete(approvalEntity);
    }

////    임시저장
//    @Transactional
//    public Long storage(ApprovalSaveDto saveDto) {
//        return approvalRepository.save(saveDto.toStorageApproval()).getApprovalNo();
//    }
//
////    임시저장중인 기안을 제출
//    @Transactional
//    public  ApprovalResponseDto updateStorage(long approvalNo, ApprovalStorageDto storageDto) {
//        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);
//        approvalEntity.updateStorage(
//                storageDto.getApprovalState()
//        );
//
//        return approvalEntity.toDto();
//    }

}
