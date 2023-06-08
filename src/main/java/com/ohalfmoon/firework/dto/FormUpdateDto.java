package com.ohalfmoon.firework.dto;

import com.ohalfmoon.firework.model.FormEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : FormUpdateDto
 * author         : 방한솔
 * date           : 2023/06/05
 * description    : 결재양식 수정 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/05        방한솔           최초 생성
 */
@Getter
@NoArgsConstructor
public class FormUpdateDto {
    private String formName;
    private String formText;
    private Boolean isUsed;

    @Builder
    public FormUpdateDto(String formName, String formText, Boolean isUsed) {
        this.formName = formName;
        this.formText = formText;
        this.isUsed = isUsed;
    }

    public FormEntity toEntity() {
        return FormEntity.builder()
                .formName(formName)
                .formText(formText)
                .isUsed(isUsed)
                .build();
    }
}
