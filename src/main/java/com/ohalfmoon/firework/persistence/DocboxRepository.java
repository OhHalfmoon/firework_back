package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.DocboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/**
 * packageName :  com.ohalfmoon.firework.persistence
 * fileName : DocboxRepository
 * author :  오상현
 * date : 2023-06-13
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-13           오상현               최초 생성
 */
public interface DocboxRepository extends JpaRepository<DocboxEntity, Long> {
    List<DocboxEntity> findAll();
}
