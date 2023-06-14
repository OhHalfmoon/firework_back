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
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageNo;

    @ManyToOne
    @JoinColumn(name = "receiver")
    private MemberEntity receiver;

    @ManyToOne
    @JoinColumn(name = "sender")
    private MemberEntity sender;

    @Column(nullable = false)
    private boolean messageCheck;

    @Column(nullable = false)
    private String messageTitle;

    @Column(nullable = false)
    private String messageContent;

    @CreatedDate
    private LocalDateTime regdate;

    public void update(boolean messageCheck){
        this.messageCheck = messageCheck;
    }
}
