package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.AlarmResponseDto;
import com.ohalfmoon.firework.dto.master.MasterLineResponseDTO;
import com.ohalfmoon.firework.dto.master.MasterLineSaveDTO;
import com.ohalfmoon.firework.dto.sub.SubLineResponseDTO;
import com.ohalfmoon.firework.dto.sub.SubLineSaveDTO;
import com.ohalfmoon.firework.dto.sub.SubLineUpdateDTO;
import com.ohalfmoon.firework.model.MasterLineEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.SubLineEntity;
import com.ohalfmoon.firework.persistence.MasterLineRepository;
import com.ohalfmoon.firework.persistence.SubLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : SubLineService
 * author         : 이지윤
 * date           : 2023/06/07
 * description    : 저장, 단일 조회, lineNo를 통한 리스트 조회, 수정, 삭제 기능
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        이지윤           최초 생성
 * 2023/06/14        이지윤           서브라인 전체 리스트 조회하는 getList 추가
 */
@Service
public class SubLineService {
    @Autowired
    private SubLineRepository subLineRepository;

    @Autowired
    private MasterLineRepository masterLineRepository;

    @Transactional
    public Long save(SubLineSaveDTO dto) {
        return subLineRepository.save(dto.toEntity()).getSubLineNo();
    }

    @Transactional
    public Long update(Long subLineNo, SubLineUpdateDTO dto) {
        SubLineEntity entity = subLineRepository.findById(subLineNo)
                .orElseThrow(() -> new IllegalArgumentException("라인이 존재하지 않습니다."));
        entity.update(dto.getOrderLevel(), dto.getUserNo());
        subLineRepository.save(entity);
        return subLineNo;
    }

    public SubLineResponseDTO findBySubLineNo(Long subLineNo) {
        SubLineEntity entity = subLineRepository
                .findById(subLineNo)
                .orElseThrow(()-> new IllegalArgumentException("라인이 존재하지 않습니다."));
        return new SubLineResponseDTO(entity);
    }

    public List<SubLineResponseDTO> getList() {
        return subLineRepository.findAll()
                .stream().map(SubLineResponseDTO::new).collect(Collectors.toList());
    }

    public List<SubLineResponseDTO> getListByLineNo(Long lineNo) {
        return subLineRepository.findAllByMasterLineEntity_LineNo(lineNo)
                .stream().map(SubLineResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long subLineNo) {
        SubLineEntity entity = subLineRepository
                .findById(subLineNo)
                .orElseThrow(() -> new IllegalArgumentException("라인이 존재하지 않습니다."));
        subLineRepository.delete(entity);
    }


}
