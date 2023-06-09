package com.ohalfmoon.firework.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

/**
 * packageName    : com.ohalfmoon.firework.model
 * fileName       : DocboxEntity
 * author         : 오상현
 * date           : 2023/06/07
 * description    : 문서함 Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        오상현           최초 생성
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_docbox")
@DynamicInsert
@Builder
@ToString
public class DocboxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long docboxNo;

    @Column(nullable = false)
    private String docboxName;

    private Date regdate;

    private Date updatedate;

    @Builder
    public DocboxEntity(
            String docboxName
    ) {
        this.docboxName = docboxName;
    }

}
