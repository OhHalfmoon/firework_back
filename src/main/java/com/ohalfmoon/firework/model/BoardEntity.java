package com.ohalfmoon.firework.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * packageName    : com.ohalfmoon.firework.model
 * fileName       : BoardEntity
 * author         : 오상현
 * date           : 2023/06/07
 * description    : 게시판 Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/07        오상현           최초 생성
 * 2023/06/26        이지윤           Attach리스트, update 추가
 */

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_board")
@DynamicInsert
@Builder
@ToString
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardNo;

    @Column(nullable = false)
    private int boardCategory;

    @ManyToOne
    @JoinColumn(name = "userNo")
    private MemberEntity memberEntity;

    @OneToMany
    @JoinColumn(name = "boardNo")
    private List<AttachEntity> attachEntities;

    @Column(nullable = false)
    String boardTitle;

    @Column(nullable = false)
    String boardContent;

    @Column(nullable = false)
    Long boardCount;

    private Date regdate;

    private Date updatedate;

    public void update(Long boardNo, String boardTitle, String boardContent) {
        this.boardNo = boardNo;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
    }
}
