package com.ohalfmoon.firework.model;

import com.ohalfmoon.firework.dto.approval.ApprovalResponseDto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

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

    public void updateStorage(String approvalName, Long lineNo, Long docboxNo, String approContent, boolean storage,int approvalState) {
        this.approvalName = approvalName;
        this.masterLineEntity = MasterLineEntity.builder().lineNo(lineNo).build();
        this.docboxEntity = DocboxEntity.builder().docboxNo(docboxNo).build();
        this.approContent = approContent;
        this.storage = storage;
        this.approvalState = approvalState;
    }

    public void updateState(int approvalState) {
        this.approvalState = approvalState;
    }
}
