package com.ohalfmoon.firework.service;
import com.ohalfmoon.firework.dto.master.MasterLineResponseDTO;
import com.ohalfmoon.firework.dto.master.MasterLineSaveDTO;
import com.ohalfmoon.firework.dto.master.MasterLineUpdateDTO;
import com.ohalfmoon.firework.dto.sub.SubLineResponseDTO;
import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.SubLineEntity;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import com.ohalfmoon.firework.persistence.MasterLineRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import com.ohalfmoon.firework.persistence.SubLineRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : MasterLineService
 * author         : 이지윤
 * date           : 2023/06/07
 * description    : 저장, 단일 조회, 리스트 조회, 수정, 삭제 기능
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        이지윤           최초 생성
 * 2023/06/21        이지윤           userNo를 통한 리스트 조회 수정(개수가 3개가 안될 경우 빈 객체를 넣어주는 기능 추가)
 */

@Service
@Slf4j
public class MasterLineService {
    @Autowired
    private MasterLineRepository masterLineRepository;

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private SubLineRepository subLineRepository;

    @Autowired
    private ApprovalRepository approvalRepository;

    @Transactional
    public Long save(MasterLineSaveDTO dto) {
        MasterLineEntity mle = masterLineRepository.save(dto.toEntity());
        AtomicInteger idx = new AtomicInteger(1);
        List<SubLineEntity> subLineEntities = dto.getUserNos().stream().map(userNo ->
                SubLineEntity.builder().orderLevel(idx.getAndIncrement()).masterLineEntity(mle).memberEntity(MemberEntity.builder().userNo(userNo).build()).build()
        ).collect(Collectors.toList());
        subLineRepository.saveAll(subLineEntities);
        return mle.getLineNo();
    }

    // lineNo를 통한 단일 조회
    public MasterLineResponseDTO findByLineNo(Long lineNo) {
        MasterLineEntity entity = masterLineRepository
                .findById(lineNo)
                .orElseThrow(()-> new IllegalArgumentException("라인이 존재하지 않습니다"));
        return new MasterLineResponseDTO(entity);
    }
    
    // userNo를 통한 리스트 조회
    public List<MasterLineResponseDTO> getList(Long userNo) {
        return masterLineRepository.findByMemberEntity_UserNo(userNo)
        .stream().map((masterLineEntity) -> {
                    MasterLineResponseDTO masterLineResponseDTO = new MasterLineResponseDTO(masterLineEntity);
                    List<SubLineResponseDTO> l = subLineRepository.
                            findAllByMasterLineEntity_LineNo(masterLineEntity.getLineNo()).
                            stream()
                            .map(SubLineResponseDTO::new)
                            .collect(Collectors.toList());
                   if(l == null) {
                       l = new ArrayList<>();
                   }
                   int size = l.size();
                   for(int i = 0 ; i < 3 - size ; i++) {
                       l.add(SubLineResponseDTO.builder().build());
                   }
                   masterLineResponseDTO.setSubLineResponseDTOS(l);
                   return masterLineResponseDTO;
            })
            .collect(Collectors.toList());
    }

    public String getMasterName(Long userNo) {
        return memberRepository.findById(userNo).orElseThrow(()-> new IllegalArgumentException("사용자가 존재하지 않습니다")).getName();
    }
    @Transactional
    public Long update(Long lineNo, MasterLineUpdateDTO dto) {
        MasterLineEntity entity = masterLineRepository
                .findById(lineNo)
                .orElseThrow(()-> new IllegalArgumentException("결재 선이 존재하지 않습니다"));
        entity.update(dto.getLineName()); //결재 선 명만 수정 가능
        masterLineRepository.save(entity);
        return lineNo;
    }

    @Transactional
    public void delete(Long lineNo) {
        MasterLineEntity entity = masterLineRepository
                .findById(lineNo)
                .orElseThrow(() -> new IllegalArgumentException("결재 선이 존재하지 않습니다"));
        subLineRepository.deleteAllByMasterLineEntity_LineNo(lineNo);
        approvalRepository.findAllByMasterLineEntity(entity).forEach(approvalEntity -> {
            approvalEntity.setMasterLineEntity(null);
        });
        masterLineRepository.delete(entity);
    }

}
