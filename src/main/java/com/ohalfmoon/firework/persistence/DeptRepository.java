package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.DeptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * packageName :  com.ohalfmoon.firework.persistence
 * fileName : DeptRepository
 * author :  ycy
 * date : 2023-06-07
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-07                ycy             최초 생성
 */
public interface DeptRepository extends JpaRepository<DeptEntity, Long> {
}