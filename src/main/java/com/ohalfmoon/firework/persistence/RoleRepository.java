package com.ohalfmoon.firework.persistence;

import com.ohalfmoon.firework.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName :  com.ohalfmoon.firework.persistence
 * fileName : RoleRepository
 * author :  ycy
 * date : 2023-06-07
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-07                ycy             최초 생성
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
