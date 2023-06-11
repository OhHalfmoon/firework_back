package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.position.PositionListResponseDTO;
import com.ohalfmoon.firework.model.PositionEntity;
import com.ohalfmoon.firework.persistence.PositionRepository;
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
 */
@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Transactional(readOnly = true)
    public List<PositionListResponseDTO> positionList() {
        System.out.println("들어왔슴");
        return positionRepository.findAll().stream()
                .map(PositionListResponseDTO::new)
                .collect(Collectors.toList());
    }
}
