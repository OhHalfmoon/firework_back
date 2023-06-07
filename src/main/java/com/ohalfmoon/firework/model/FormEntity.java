package com.ohalfmoon.firework.model;

import lombok.*;

import javax.persistence.*;

/**
 * packageName    : com.ohalfmoon.firework.model
 * fileName       : FormEntity
 * author         : 방한솔
 * date           : 2023/06/05
 * description    : 결재양식 Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/05        방한솔           최초 생성
 */
@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "tbl_form")
@EqualsAndHashCode(callSuper = false)
public class FormEntity extends BaseTimeEntity {
    // 결재양식 Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long formNo;

    // 결재양식 명
    private String formName;

    // 결재양식 내용
    private String formText;

    // 양식 사용수
    private int useCount;

    // 사용여부
    private Boolean isUsed;

    /**
     * FormEntity 생성자
     *
     * @param formName 결재양식 명
     * @param formText 결재양식 내용
     * @param isUsed   사용여부
     */
    @Builder
    public FormEntity(String formName, String formText, Boolean isUsed) {
        this.formName = formName;
        this.formText = formText;
        this.isUsed = isUsed;
    }
    public void update(Long formNo, String formName, String formText, Boolean isUsed){
        this.formNo = formNo;
        this.formName = formName;
        this.formText = formText;
        this.isUsed = isUsed;
    }


}
