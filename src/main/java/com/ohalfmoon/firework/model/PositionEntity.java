package com.ohalfmoon.firework.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_position")
@DynamicInsert
@ToString
@Builder
public class PositionEntity {
    @Id
    private Long positionNo;
    private String positionName;
    private Date regdate;
    private Date updatedate;
}
