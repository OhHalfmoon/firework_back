package com.ohalfmoon.firework.dto.fileUpload;

import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.AttachEntity;
import com.ohalfmoon.firework.model.BoardEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * packageName    : com.ohalfmoon.firework.dto.fileUpload
 * fileName       : AttachSaveDto
 * author         : banghansol
 * date           : 2023/06/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/19        banghansol       최초 생성
 */
@Getter
@Setter
@ToString
public class AttachSaveDto {
    Long boardNo;

    Long approvalNo;

    // uuid
    String uuid;

    // 원본 파일 이름
    String originName;

    // 파일 경로
    String path;

    // 확장자
    String ext;

    @Builder
    public AttachSaveDto(Long boardNo, Long approvalNo, String uuid, String originName, String path, String ext) {
        this.boardNo = boardNo;
        this.approvalNo = approvalNo;
        this.uuid = uuid;
        this.originName = originName;
        this.path = path;
        this.ext = ext;
    }

    public AttachEntity toEntity(){
        AttachEntity entity = AttachEntity.builder()
                .uuid(uuid)
                .path(path)
                .originName(originName)
                .ext(ext)
                .build();

        if(this.approvalNo != null){
            ApprovalEntity approvalEntity = ApprovalEntity.builder()
                    .approvalNo(approvalNo)
                    .build();

            entity.updateApprovalEntity(approvalEntity);
        }

        if(this.boardNo != null){
            BoardEntity boardEntity = BoardEntity.builder()
                    .boardNo(boardNo)
                    .build();

            entity.updateBoardEntity(boardEntity);
        }

        return entity;
    }
}
