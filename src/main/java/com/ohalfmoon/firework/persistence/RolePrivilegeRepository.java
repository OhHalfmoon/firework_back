package com.ohalfmoon.firework.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * packageName    : com.ohalfmoon.firework.persistence
 * fileName       : RolePrivilegeRepository
 * author         : banghansol
 * date           : 2023/06/13
 * description    : 역할-권한 관계 repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/13        방한솔            최초 생성
 */
public interface RolePrivilegeRepository extends JpaRepository<RolePrivilegeRepository, Long> {
}
