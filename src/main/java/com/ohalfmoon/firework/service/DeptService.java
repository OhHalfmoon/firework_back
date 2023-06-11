package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.dept.DeptListResponseDTO;
import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.persistence.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName :  com.ohalfmoon.firework.service
 * fileName : DeptService
 * author :  ycy
 * date : 2023-06-09
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-09                ycy             최초 생성
 */
@Service
public class DeptService {

    @Autowired
    private DeptRepository deptRepository;

    @Transactional(readOnly = true)
    public List<DeptListResponseDTO> deptList() {
        return deptRepository.findAll().stream() // 읽기전용의 stream화
                .map(DeptListResponseDTO::new) // map을 통해 DeptListResponseDTO객체 생성
                .collect(Collectors.toList()); // toList 출력
    }


}
