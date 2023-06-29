package com.ohalfmoon.firework.dto.fileUpload;

import com.ohalfmoon.firework.service.AttachService;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * packageName    : com.ohalfmoon.firework.dto.fileUpload
 * fileName       : AttachResponseDto
 * author         : banghansol
 * date           : 2023/06/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/19        banghansol       최초 생성
 */
@Getter
@ToString
public class AttachResponseDto {
    Long attachNo;

    // uuid
    String uuid;

    // 원본 파일 이름
    String originName;

    // 파일 url
    String url;

    // 확장자
    String ext;

    // 위치
    String path;

    public File getFile() {
        return new File(AttachService.uploadDir + File.separator + path,uuid + "." + getExt());
    }

    @Builder
    public AttachResponseDto(Long attachNo, String uuid, String originName, String url, String ext, String path) {
        this.attachNo = attachNo;
        this.uuid = uuid;
        this.originName = originName;
        this.url = url;
        this.ext = ext;
        this.path = path;
    }
}
