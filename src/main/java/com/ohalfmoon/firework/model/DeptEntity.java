package com.ohalfmoon.firework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_dept")
@DynamicInsert
public class DeptEntity {
    @Id
    private Long deptNo;
    private String deptName;
    private Date regdate;
    private Date updatedate;
}
