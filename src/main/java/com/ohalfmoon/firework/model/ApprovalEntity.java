package com.ohalfmoon.firework.model;

import com.ohalfmoon.firework.dto.ApprovalResponseDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "tbl_approval")
@DynamicInsert
@Builder
@ToString
public class ApprovalEntity extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long approvalNo;

    @Column(nullable = false)
    private String approvalName;

    @ManyToOne
    @JoinColumn(name = "formNo")
    private FormEntity formEntity;

    @ManyToOne
    @JoinColumn(name = "lineNo")
    private MasterLineEntity masterLineEntity;

    @ManyToOne
    @JoinColumn(name = "docboxNo")
    private DocboxEntity docboxEntity;

    @Column(nullable = false)
    private String approContent;

    @ManyToOne
    @JoinColumn(name = "userNo")
    private MemberEntity memberEntity;

    @Column(nullable = false)
    private boolean storage;

    @Column(nullable = false)
    private int approvalState;

//    private Date regdate;
//
//    private Date updatedate;


    public ApprovalResponseDto toDto() {
        return ApprovalResponseDto.builder()
                .approvalNo(approvalNo)
                .approvalName(approvalName)
                .docboxNo(docboxEntity.getDocboxNo())
                .docboxName(docboxEntity.getDocboxName())
                .approContent(approContent)
                .userNo(memberEntity.getUserNo())
                .name(memberEntity.getName())
                .storage(storage)
                .approvalState(approvalState)
                .regdate(getRegdate())
                .build();
    }

    public void updateStorage(boolean storage) {
        this.storage = storage;
    }

    public void updateState(int approvalState) {
        this.approvalState = approvalState;
    }
}
