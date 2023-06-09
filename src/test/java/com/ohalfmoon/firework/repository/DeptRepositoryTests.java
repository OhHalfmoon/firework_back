package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.persistence.DeptRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

/**
 * packageName :  com.ohalfmoon.firework.repository
 * fileName : DeptRepositoryTests
 * author :  ycy
 * date : 2023-06-09
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-09                ycy             최초 생성
 */
@SpringBootTest
@Slf4j
public class DeptRepositoryTests {

    @Autowired
    DeptRepository deptRepository;

    @Test
    public void deptListTests() {
        List<DeptEntity> deptList = deptRepository.findAll();
        log.info("{}", deptList);

    }
}
