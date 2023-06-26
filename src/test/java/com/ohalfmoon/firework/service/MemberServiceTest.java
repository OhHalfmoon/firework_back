package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.fileUpload.AttachSaveDto;
import com.ohalfmoon.firework.dto.member.*;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.Role;
import com.ohalfmoon.firework.model.State;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

/**
 * packageName :  com.ohalfmoon.firework.service
 * fileName : MemberServiceTest
 * author :  ycy
 * date : 2023-06-14
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-14                ycy             최초 생성
 */
@SpringBootTest
@Slf4j
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    AttachService attachService;

    @Autowired
    PasswordEncoder encoder;

    @Test
    @DisplayName("비밀번호 변경 테스트 및 기존 암호화되지 않은 비밀번호 암호화")
    public void updatePwTests() {
        MemberUpdatePwDTO dto = new MemberUpdatePwDTO();
//        for(long i = 1L; i<=14L; i++) {
        dto.setPassword(encoder.encode("1234"));
        dto.toEntity();
        memberService.updatePw(1L, dto);
//        }
    }
    @Test
    @DisplayName("회원 상태값 변경 테스트")
    public void updateState() {
        MemberUpdateStateDTO dto = new MemberUpdateStateDTO();
        dto.setState(State.WATING);
        dto.toEntity();
        memberService.recognize(2L, dto);
    }

    @Test
    @DisplayName("회원 정보 수정")
    public void updateTest() {
        MemberUpdateDTO dto = new MemberUpdateDTO();
        dto.setName("이지윤2");
        dto.setDeptNo(1L);
        dto.setPositionNo(100L);
        dto.setPhoneNum("01022223333");
        dto.setEmail("bnoc@naver.com");
        dto.toEntity();
        memberService.update(2L, dto);
    }

    @Test
    @DisplayName("회원정보(관리자)")
    public void updateByAdminTest() {
        MemberUpdateByAdminRequestDTO dto = new MemberUpdateByAdminRequestDTO();
        dto.setName("관리자가 수정");
        dto.setState(State.SECESSION); // 회원 탈퇴
        dto.setRoleName(Role.ROLE_TL);
        dto.setDeptNo(2L);
        dto.setPositionNo(10L);
        memberService.updateByAdmin(18L, dto);
    }

    @Test
    @DisplayName("회원 List 조회")
    public void memberList() {
        memberService.getMemberList();
        log.info("Member List : {}", memberService.getMemberList());
    }

    @Test
    @DisplayName("회원 단일조회")
    public void getMember() {
        memberService.get(2L);
        log.info("memberGet : {}", memberService.get(2L));
    }


    @Test
    public void saveFileTest() throws IOException {
        MockMultipartFile file = new MockMultipartFile("signtest", "image-removebg-preview.png", "image/png", "Hello, World!".getBytes());

        AttachSaveDto dto = AttachSaveDto.builder()
                .uuid(UUID.randomUUID().toString())
                .ext(FilenameUtils.getExtension(file.getOriginalFilename()))
                .originName(file.getOriginalFilename())
                .build();

//        Long attachNo = attachService.fileSave(dto, file);
//        log.info("파일테스트,{}", attachNo);

        Long userNo = memberService.get(2L).getUserNo();
//        MemberUpdateAttachDTO updateAttachDTO = MemberUpdateAttachDTO.builder()
//                .attachNo(atta)
//                .build();

        Long testUser = memberService.updateSign(dto, file, userNo);

        log.info("유저테스트,{}", testUser);



    }
}
