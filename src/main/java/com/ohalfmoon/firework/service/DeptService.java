package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.persistence.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    List<DeptEntity> deptEntityList() {
        return deptRepository.findAll();
    }
}
