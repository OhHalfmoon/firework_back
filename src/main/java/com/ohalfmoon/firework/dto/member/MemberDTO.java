package com.ohalfmoon.firework.dto.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.Role;
import com.ohalfmoon.firework.model.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * packageName :  com.ohalfmoon.firework.dto.member
 * fileName : MemberDTO
 * author :  ycy
 * date : 2023-06-09
 * description : view에서 전달받은 회원가입 정보를 매핑하여 entity로 전송
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-01                ycy             최초 생성
 * 2023-06-20                ycy             유효성검증 추가
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
    private Long userNo;
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-z0-9]{4,20}$", message = "아이디는 영어 소문자와 숫자만 사용하여 5~12자리여야 합니다.")
    private String username;
    @NotBlank(message = "비밀번호는 필수 입력 값 입니다.")
    @Pattern(regexp="(^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$)",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;
    @NotBlank(message = "연락처는 필수 입력 값입니다. '-'기호는 제외하고 입력해주세요.")
    private String phoneNum;
    private Long deptNo;
    private Long positionNo;
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String name;
    private boolean manager;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "입사일은 필수 입력 값 입니다.")
    private LocalDate birthdate;
    private String authProvider;
    private String memberSign;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "입사일은 필수 입력 값 입니다.")
    private LocalDate startdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate enddate;
    private int state;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate regdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updatedate;
    private int roleName;

    @Builder
    public MemberDTO(String username
            , String password
            , String email
            , String phoneNum
            , String name
            , LocalDate birthdate
            , LocalDate startdate
            , int roleName
            , boolean manager
    ) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.name = name;
        this.birthdate = birthdate;
        this.startdate = startdate;
        this.roleName = roleName;
        this.manager = manager;
    }
    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .username(username)
                .password(password)
                .email(email)
                .phoneNum(phoneNum)
                .name(name)
                .birthdate(birthdate)
                .startdate(startdate)
                .roleName(Role.ROLE_EMPLOYEE)
                .state(State.WATING)
                .manager(false)
                .build();
    }

}