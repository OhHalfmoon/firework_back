package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.FormEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.ohalfmoon.firework.persistence
 * fileName       : FormRepository
 * author         : 방한솔
 * date           : 2023/06/05
 * description    : 결재양식 Repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/05        방한솔           최초 생성
 * 2023/06/07        방한솔           페이징, 양식명 검색기능 추가
 */
public interface FormRepository extends JpaRepository<FormEntity, Long> {
    Page<FormEntity> findByFormNameContaining(String formName, Pageable pageable);
}
