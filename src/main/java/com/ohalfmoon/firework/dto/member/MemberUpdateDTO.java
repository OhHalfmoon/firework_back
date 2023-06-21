package com.ohalfmoon.firework.dto.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ohalfmoon.firework.model.MemberEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * packageName :  com.ohalfmoon.firework.dto.member
 * fileName : MemberUpdateDTO
 * author :  ycy
 * date : 2023-06-08
 * description : 수정된 회원의 정보를 매핑하여 entity로 전달
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-08                ycy             최초 생성
 */
@Data
@NoArgsConstructor
public class MemberUpdateDTO {

    private String username;

    private String password;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "연락처는 필수 입력 값입니다. '-'기호는 제외하고 입력해주세요.")
    private String phoneNum;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private LocalDate birthdate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    @NotNull(message = "입사일은 필수 입력 값 입니다.")
    private LocalDate startdate;

    private Long deptNo;

    private Long positionNo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd", timezone = "Asia/Seoul")
    private LocalDateTime updatedate;

    @Builder
    public MemberUpdateDTO(String email, String phoneNum, String name, LocalDate birthdate, LocalDate startdate, Long deptNo, Long positionNo, LocalDateTime updatedate) {
        this.email = email;
        this.phoneNum = phoneNum;
        this.name = name;
        this.birthdate = birthdate;
        this.startdate = startdate;
        this.deptNo = deptNo;
        this.positionNo = positionNo;
        this.updatedate = updatedate;
    }

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .email(email)
                .phoneNum(phoneNum)
                .name(name)
                .birthdate(birthdate)
                .startdate(startdate)
                .updatedate(LocalDateTime.now())
                .build();
    }
}
