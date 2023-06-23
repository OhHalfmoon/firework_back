package com.ohalfmoon.firework.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

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
