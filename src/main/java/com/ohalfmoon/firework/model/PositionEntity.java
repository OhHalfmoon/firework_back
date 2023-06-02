package com.ohalfmoon.firework.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
public class PositionEntity {
    @Id
    private Long positionNo;
    private String positionName;
    private Date regdate;
    private Date updatedate;
}
