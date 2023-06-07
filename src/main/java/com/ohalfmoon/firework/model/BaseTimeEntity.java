package com.ohalfmoon.firework.model;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * packageName    : com.ohalfmoon.firework.model
 * fileName       : BaseTimeEntity
 * author         : banghansol
 * date           : 2023/06/05
 * description    : 생성시간, 수정시간 공통 엔티티
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/05        banghansol       최초 생성
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime regdate;

    @LastModifiedDate
    private LocalDateTime updatedate;

}
