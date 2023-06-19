package com.ohalfmoon.firework.dto.fileUpload;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

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

    @Builder
    public AttachResponseDto(Long attachNo, String uuid, String originName, String url, String ext) {
        this.attachNo = attachNo;
        this.uuid = uuid;
        this.originName = originName;
        this.url = url;
        this.ext = ext;
    }
}
