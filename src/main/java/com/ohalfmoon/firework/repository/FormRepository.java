package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.FormEntity;
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
 */
public interface FormRepository extends JpaRepository<FormEntity, Long> {

}
