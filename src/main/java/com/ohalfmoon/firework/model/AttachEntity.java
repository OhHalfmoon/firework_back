package com.ohalfmoon.firework.model;

import com.ohalfmoon.firework.dto.fileUpload.AttachSaveDto;
import lombok.*;

import javax.persistence.*;

/**
 * packageName    : com.ohalfmoon.firework.model
 * fileName       : AttachEntity
 * author         : banghansol
 * date           : 2023/06/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/19        banghansol       최초 생성
 */
@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "tbl_attach")
@EqualsAndHashCode(callSuper = false)
public class AttachEntity extends BaseTimeEntity {
    // 첨부파일 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attachNo;

    @ManyToOne
    @JoinColumn(name = "boardNo")
    BoardEntity boardEntity;

    @ManyToOne
    @JoinColumn(name = "approvalNo")
    ApprovalEntity approvalEntity;

    // uuid
    String uuid;

    // 원본 파일 이름
    String originName;

    // 파일 경로
    String path;

    // 확장자
    String ext;

    @Builder
    public AttachEntity(Long attachNo, String uuid, String originName, String path, String ext) {
        this.attachNo = attachNo;
        this.uuid = uuid;
        this.originName = originName;
        this.path = path;
        this.ext = ext;
    }

    public void updateBoardEntity(BoardEntity boardEntity) {
        this.boardEntity = boardEntity;
    }

    public void updateApprovalEntity(ApprovalEntity approvalEntity) {
        this.approvalEntity = approvalEntity;
    }
}
