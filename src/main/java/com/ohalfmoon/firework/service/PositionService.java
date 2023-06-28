package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.position.PositionListResponseDTO;
import com.ohalfmoon.firework.model.PositionEntity;
import com.ohalfmoon.firework.persistence.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName :  com.ohalfmoon.firework.service
 * fileName : PositionService
 * author :  ycy
 * date : 2023-06-09
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-09                ycy             최초 생성
 * 2023-06-09                ycy            PositionList 추가
 */
@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;

    /**
     * Position list 조회
     *
     * @return the list
     */
    @Transactional(readOnly = true)
    public List<PositionListResponseDTO> positionList() {
        return positionRepository.findAll().stream()
                .map(PositionListResponseDTO::new)
                .collect(Collectors.toList());
    }
}
