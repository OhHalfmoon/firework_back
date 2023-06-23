package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.approval.*;
import com.ohalfmoon.firework.model.*;
import com.ohalfmoon.firework.persistence.AlarmRepository;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import com.ohalfmoon.firework.persistence.SubLineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
 * 2023/06/19        우성준           결재 생성 시 알림 추가
 * 2023/06/22        오상현           회원번호와 결재상태값을 통한 기안 리스트 조회
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ApprovalService {
    private final ApprovalRepository approvalRepository;
    private final SubLineRepository subLineRepository;
    private final AlarmRepository alarmRepository;
    private final MemberRepository memberRepository;
    private final JdbcTemplate jdbcTemplate;


    //기안 제출(결재)
    @Transactional
    public Long register(ApprovalSaveDto saveDto) {
         Long no = approvalRepository.save(saveDto.toSaveApproval()).getApprovalNo();
            alarmRepository.save(AlarmEntity.builder()
                .approvalNo(ApprovalEntity.builder().approvalNo(no).build())
                .alarmCategory("결제요청")
                .alarmTitle("새로운 결제요청-"+memberRepository.findById(saveDto.getUserNo()).orElse(null).getName())
                .alarmReceiver(MemberEntity.builder().userNo(getApprovalUserName(no).get(0).getUserNo()).build())
                .boardNo(null)
                .build());
         return no;
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

    //회원번호를 통한 기안 리스트 조회
    public List<ApprovalResponseDto> getMyList (final Long userNo) {
        return approvalRepository
                .findAllByMemberEntity(MemberEntity.builder().userNo(userNo).build())
                .stream().map(ApprovalResponseDto::new).collect(Collectors.toList());
    }

    //회원번호와 결재상태값을 통한 임시저장 리스트 조회
    public List<ApprovalResponseDto> getStateList (final Long userNo, int approvalState) {

        return approvalRepository
                .findAllByMemberEntityAndApprovalState(MemberEntity.builder().userNo(userNo).build(),approvalState)
                .stream().map(ApprovalResponseDto::new).collect(Collectors.toList());
    }

    //기안번호를 통한 해당기안의 결재자 리스트 조회
    public List<ApprovalLineDto> getApprovalUserName (final Long approvalNo) {
        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);
        MasterLineEntity masterLineEntity = approvalEntity.getMasterLineEntity();



       return subLineRepository.findAllByMasterLineEntity_LineNo(masterLineEntity.getLineNo())
                .stream().map(ApprovalLineDto::new).collect(Collectors.toList());

    }

//    public List<ApprovalResponseDto>getSublineUser (final Long userNo, int approvalState) {
//        List<SubLineEntity> sub = subLineRepository.findAllByMemberEntity(MemberEntity.builder().userNo(userNo).build());
//
//        List<ApprovalEntity> result = new ArrayList<>();
//        sub.forEach(s -> {
////            result.addAll(approvalRepository.findAllByMasterLineEntity(s.getMasterLineEntity()));
//            result.addAll(approvalRepository.findAllByMasterLineEntityAndApprovalState(s.getMasterLineEntity(),approvalState));
//        });
//        return result.stream().map(ApprovalResponseDto::new).collect(Collectors.toList());
//
//    }

    public List<ApprovalResponseDto>getSublineUser (final Long userNo) {
        return jdbcTemplate.query(
        "select approvalNo,approvalName, docboxName,userNo, name, approvalOrder, approvalState, ta.regdate " +
                "from (tbl_sub_line su join tbl_approval ta using(lineNo)) join tbl_member tm using(userNo) join tbl_docbox td using(docboxNo) " +
                "where su.subMemberNo = ? and ta.approvalState = 1 and su.orderLevel = ta.approvalOrder order by ta.approvalNo desc"
        , new RowMapper<ApprovalResponseDto>() {
            @Override
            @Nullable
            public ApprovalResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long approvalNo = rs.getLong("approvalNo");
                String approvalName = rs.getString("approvalName");
                String docboxName = rs.getString("docboxName");
                Long userNo = rs.getLong("userNo");
                String name = rs.getString("name");
                int approvalOrder = rs.getInt("approvalOrder");
                int approvalState = rs.getInt("approvalState");
                LocalDate regdate = rs.getDate("regdate").toLocalDate();
                return ApprovalResponseDto.builder()
                        .approvalNo(approvalNo)
                        .approvalName(approvalName)
                        .docboxName(docboxName)
                        .userNo(userNo)
                        .name(name)
                        .approvalOrder(approvalOrder)
                        .approvalState(approvalState)
                        .regdate(regdate)
                        .build();
            }
        }, userNo);

    }

    //결재 서류 수정
    @Transactional
    public Long update(long approvalNo, ApprovalUpdateDto updateDto) {
                ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);

                approvalEntity.update(
                        updateDto.getApprovalName(),
                        updateDto.getLineNo(),
                        updateDto.getDocboxNo(),
                        updateDto.getApproContent(),
                        updateDto.getApprovalOrder(),
                        updateDto.getApprovalState(),
                        updateDto.getRegdate()
                );
                if (approvalEntity.getApprovalOrder() == 1) {
                    List<ApprovalLineDto> lineDtos = getApprovalUserName(approvalNo);
                    alarmRepository.save(AlarmEntity.builder()
                            .approvalNo(ApprovalEntity.builder().approvalNo(approvalNo).build())
                            .alarmCategory("결재요청")
                            .alarmTitle("새로운 결재요청-"+memberRepository.findById(approvalRepository.findByApprovalNo(approvalNo).getMemberEntity().getUserNo()).orElse(null).getName())
                            .alarmReceiver(MemberEntity.builder().userNo(lineDtos.get(0).getUserNo()).build())
                            .boardNo(null)
                            .build());
                    return approvalNo;
                }
                return approvalNo;
    }

    //기안 상태값을 변경
    @Transactional
    public  Long updateState(Long approvalNo , ApprovalStateDto stateDto) {
        List<ApprovalLineDto> lineDtos = getApprovalUserName(approvalNo);

        for (int i = 0; i < lineDtos.size(); i++) {
            if (i == lineDtos.size()-1) {
                ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);
                approvalEntity.updateState(
                        stateDto.getApproContent(),
                        i+1,
                        2
                );
                alarmRepository.save(AlarmEntity.builder()
                        .approvalNo(ApprovalEntity.builder().approvalNo(approvalNo).build())
                        .alarmCategory("결재")
                        .alarmTitle("결재완료-"+approvalNo)
                        .alarmReceiver(memberRepository.findById(approvalRepository.findByApprovalNo(approvalNo).getMemberEntity().getUserNo()).orElse(null))
                        .boardNo(null)
                        .build());
                return approvalNo;

            } else if (lineDtos.get(i).getOrderLevel() == approvalRepository.findByApprovalNo(approvalNo).getApprovalOrder()) {
                ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);

                approvalEntity.updateState(
                        stateDto.getApproContent(),
                        stateDto.getApprovalOrder(),
                        stateDto.getApprovalState()
                );
                alarmRepository.save(AlarmEntity.builder()
                        .approvalNo(ApprovalEntity.builder().approvalNo(approvalNo).build())
                        .alarmCategory("결재요청")
                        .alarmTitle("새로운 결재요청-"+memberRepository.findById(approvalRepository.findByApprovalNo(approvalNo).getMemberEntity().getUserNo()).orElse(null).getName())
                        .alarmReceiver(MemberEntity.builder().userNo(lineDtos.get(i+1).getUserNo()).build())
                        .boardNo(null)
                        .build());
                
                return approvalNo;
            }
        }
        return approvalNo;
    }
    //반려
    @Transactional
    public  Long rejectState(Long approvalNo , ApprovalStateDto stateDto) {
        List<ApprovalLineDto> lineDtos = getApprovalUserName(approvalNo);
        for (int i = 0; i < lineDtos.size(); i++) {
                if (lineDtos.get(i).getOrderLevel() == approvalRepository.findByApprovalNo(approvalNo).getApprovalOrder()) {
                ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);
                approvalEntity.updateState(
                        stateDto.getApproContent(),
                        0,
                        0
                );
                alarmRepository.save(AlarmEntity.builder()
                        .approvalNo(ApprovalEntity.builder().approvalNo(approvalNo).build())
                        .alarmCategory("반려")
                        .alarmTitle("결재반려-"+approvalNo)
                        .alarmReceiver(memberRepository.findById(approvalRepository.findByApprovalNo(approvalNo).getMemberEntity().getUserNo()).orElse(null))
                        .boardNo(null)
                        .build());
                return approvalNo;
            }
        }
        return approvalNo;
    }

    //기안 삭제
    @Transactional
    public void delete(Long approvalNo) {
        ApprovalEntity approvalEntity = approvalRepository.findByApprovalNo(approvalNo);
        List<AlarmEntity> alarmEntities = alarmRepository.findAllByApprovalNo(ApprovalEntity.builder().approvalNo(approvalNo).build());
        if (alarmEntities.size() > 0) {
            for (AlarmEntity entity : alarmEntities ) {
                alarmRepository.deleteById(entity.getAlarmNo());
            }
        }
        approvalRepository.delete(approvalEntity);
    }
}
