package com.ohalfmoon.firework.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * packageName    : com.ohalfmoon.firework.model
 * fileName       : MessageEntity
 * author         : 우성준
 * date           : 2023/06/13
 * description    : 쪽지 Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/13        우성준            최초생성
 * 2023/06/19        우성준            메시지 수정시간(updatedate) 추가
 */

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_message")
@DynamicInsert
@Builder
@EqualsAndHashCode()
@ToString
@EntityListeners(AuditingEntityListener.class)
public class MessageEntity extends BaseTimeEntity {

    // 쪽지일련번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageNo;

    // 쪽지 받은 사원객체(사원일련번호로)
    @ManyToOne
    @JoinColumn(name = "receiver")
    private MemberEntity receiver;

    // 쪽지 보낸 사원객체(사원일련번호로)
    @ManyToOne
    @JoinColumn(name = "sender")
    private MemberEntity sender;

    // 쪽지 확인 여부
    @Column(nullable = false)
    private boolean messageCheck;

    // 쪽지 제목
    @Column(nullable = false)
    private String messageTitle;

    // 쪽지 내용
    @Column(nullable = false)
    private String messageContent;

    // 쪽지 확인 업데이트
    public void update(boolean messageCheck){
        this.messageCheck = messageCheck;
    }
}
