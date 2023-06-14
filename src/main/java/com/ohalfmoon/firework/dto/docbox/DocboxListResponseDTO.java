package com.ohalfmoon.firework.dto.docbox;

import com.ohalfmoon.firework.model.DocboxEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * packageName :  com.ohalfmoon.firework.dto.form
 * fileName : DocboxDTO
 * author :  오상현
 * date : 2023-06-13
 * description : 문서함 ResponseDTO
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-13           오상현               최초 생성
 */
@NoArgsConstructor
@Data
public class DocboxListResponseDTO {
    private Long docboxNo;
    private String docboxName;

    public DocboxListResponseDTO(DocboxEntity docboxEntity) {
        docboxNo = docboxEntity.getDocboxNo();
        docboxName = docboxEntity.getDocboxName();
    }
}
