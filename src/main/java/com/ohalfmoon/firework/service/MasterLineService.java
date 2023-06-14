package com.ohalfmoon.firework.service;
import com.ohalfmoon.firework.dto.AlarmResponseDto;
import com.ohalfmoon.firework.dto.master.MasterLineResponseDTO;
import com.ohalfmoon.firework.dto.master.MasterLineSaveDTO;
import com.ohalfmoon.firework.dto.master.MasterLineUpdateDTO;
import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.persistence.MasterLineRepository;
import com.ohalfmoon.firework.persistence.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
 */

@Service
public class MasterLineService {
    @Autowired
    private MasterLineRepository masterLineRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    public Long save(MasterLineSaveDTO dto) {
        return masterLineRepository.save(dto.toEntity()).getLineNo();
    }

    // lineNo를 통한 단일 조회
    public MasterLineResponseDTO findByLineNo(Long lineNo) {
        MasterLineEntity entity = masterLineRepository
                .findById(lineNo)
                .orElseThrow(()-> new IllegalArgumentException("라인이 존재하지 않습니다"));
        return new MasterLineResponseDTO(entity);
//        entity.getLineName();
//        entity.getUserNo().getUsername();
    }
    
    // userNo를 통한 리스트 조회
    public List<MasterLineResponseDTO> getList(Long userNo) {
        return masterLineRepository.findByMemberEntity_UserNo(userNo)
                .stream().map(MasterLineResponseDTO::new).collect(Collectors.toList());
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
        masterLineRepository.delete(entity);

    }
}
