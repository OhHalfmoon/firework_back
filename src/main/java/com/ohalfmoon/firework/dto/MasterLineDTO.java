package com.ohalfmoon.firework.dto;

import com.ohalfmoon.firework.model.MasterLineEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;

/**
 * packageName    : com.ohalfmoon.firework.dto
 * fileName       : MasterLineDTO
 * author         : 이지윤
 * date           : 2023/06/07
 * description    : 결재 선에 필요한 정보(기안자, 결재선 명)DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        이지윤           최초 생성
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterLineDTO {
    private Long lineNo;
    private String lineName;
    private Long userNo;
    private Date regdate;
    private Date updatedate;

    public MasterLineDTO(final MasterLineEntity entity) {
        this.lineNo = entity.getLineNo();
        this.lineName = entity.getLineName();
        this.userNo = entity.getUserNo();
        this.regdate = entity.getRegdate();
        this.updatedate = entity.getUpdatedate();
    }

    public MasterLineEntity toEntity(){
        return MasterLineEntity.builder()
                .lineNo(lineNo)
                .lineName(lineName)
                .userNo(userNo)
                .build();
    }
}
