package com.ohalfmoon.firework.dto.fileUpload;

import com.ohalfmoon.firework.model.ApprovalEntity;
import com.ohalfmoon.firework.model.AttachEntity;
import com.ohalfmoon.firework.model.BoardEntity;
import com.ohalfmoon.firework.service.AttachService;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

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
public class AttachDto {
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
    public AttachDto(Long boardNo, Long approvalNo, String uuid, String originName, String path, String ext) {
        this.boardNo = boardNo;
        this.approvalNo = approvalNo;
        this.uuid = uuid;
        this.originName = originName;
        this.path = path;
        this.ext = ext;
    }

    public AttachDto(AttachEntity entity) {
        uuid = entity.getUuid();
        originName = entity.getOriginName();
        path = entity.getPath();
        ext = entity.getExt();
        approvalNo = Optional.ofNullable(entity.getApprovalEntity())
                .map(ApprovalEntity::getApprovalNo).orElse(null);
        boardNo = Optional.ofNullable(entity.getBoardEntity())
                .map(BoardEntity::getBoardNo).orElse(null);
    }

    public AttachDto(MultipartFile file) {
        originName = file.getOriginalFilename();
        uuid = UUID.randomUUID().toString();
        path = getTodayStr();
//        path = ;
        ext = FilenameUtils.getExtension(file.getOriginalFilename());
    }


    public File getFile() {
        return new File(AttachService.uploadDir + File.separator + path,uuid + "." + getExt());
    }

    public String getTodayStr() {
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
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
