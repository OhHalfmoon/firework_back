package com.ohalfmoon.firework.dto;

import com.ohalfmoon.firework.model.FormEntity;
import lombok.*;

import java.time.LocalDateTime;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : FormResponseDto
 * author         : 방한솔
 * date           : 2023/06/05
 * description    : 결재양식 조회 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/05        방한솔           최초 생성
 */
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class FormResponseDto {
    private Long formNo;
    private String formName;
    private String formText;
    private Boolean isUsed;
    private LocalDateTime regdate;

    public FormResponseDto(FormEntity entity) {
        formNo = entity.getFormNo();
        formName = entity.getFormName();
        formText = entity.getFormText();
        isUsed = entity.getIsUsed();
        regdate = entity.getRegdate();
    }
}
