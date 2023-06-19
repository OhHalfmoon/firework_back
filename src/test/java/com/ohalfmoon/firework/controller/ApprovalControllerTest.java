package com.ohalfmoon.firework.controller;

import com.ohalfmoon.firework.dto.approval.ApprovalSaveDto;
import com.ohalfmoon.firework.dto.approval.ApprovalStateDto;
import com.ohalfmoon.firework.dto.approval.ApprovalUpdateDto;
import com.ohalfmoon.firework.model.*;
import com.ohalfmoon.firework.persistence.ApprovalRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApprovalControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ApprovalRepository approvalRepository;

//    @After()
//    public void tearDown() throws Exception {
//        approvalRepository.deleteAll();
//    }

    @Test
    @Rollback(value = false)
    @Transactional
    public void ApprovalRegister() throws Exception {
        String approvalName = "기안이름";
        Long formNo = 1L;
        Long lineNo = 1L;
        Long docboxNo= 1L;
        String approContent = "서비스테스트작성중";
        Long userNo = 1L;
        int approvalState = 0;
        Date regdate = new Date();
        ApprovalSaveDto saveDto = ApprovalSaveDto.builder()
                .approvalName(approvalName)
                .formNo(formNo)
                .lineNo(lineNo)
                .docboxNo(docboxNo)
                .approContent(approContent)
                .userNo(userNo)
                .approvalState(approvalState)
                .regdate(regdate)
                .build();

        String url = "http://localhost:" + port + "/approval/";

        ResponseEntity<Long> approvalEntity = restTemplate.postForEntity(url, saveDto, Long.class);
        assertThat(approvalEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(approvalEntity.getBody()).isGreaterThan(2L);

        List<ApprovalEntity> all = approvalRepository.findAll();
        assertThat(all.get(2).getApprovalName()).isEqualTo(approvalName);
        assertThat(all.get(2).getFormEntity().getFormNo()).isEqualTo(formNo);
        assertThat(all.get(2).getMasterLineEntity().getLineNo()).isEqualTo(lineNo);
        assertThat(all.get(2).getDocboxEntity().getDocboxNo()).isEqualTo(docboxNo);
        assertThat(all.get(2).getApproContent()).isEqualTo(approContent);
        assertThat(all.get(2).getMemberEntity().getUserNo()).isEqualTo(userNo);
        assertThat(all.get(2).getApprovalState()).isEqualTo(approvalState);
        assertThat(all.get(2).getRegdate()).isEqualTo(regdate);
    }

    @Test
    public void ApprovalUpadateState() throws Exception {
        ApprovalEntity updateState = approvalRepository.save(ApprovalEntity.builder()
                .approvalName("상태변경test")
                .formEntity(FormEntity.builder().formNo(1L).build())
                .masterLineEntity(MasterLineEntity.builder().lineNo(1L).build())
                .docboxEntity(DocboxEntity.builder().docboxNo(1L).build())
                .approContent("컨트롤러기안상태변경테스트기안")
                .memberEntity(MemberEntity.builder().userNo(1L).build())
                .approvalState(0)
                .regdate(new Date())
                .build());

        Long updateId = updateState.getApprovalNo();
        int expectedState = 1;
        ApprovalStateDto stateDto = ApprovalStateDto.builder().approvalState(expectedState).build();
        String url = "http://localhost:" + port + "/approval/" + "/state/" + updateId;

        HttpEntity<ApprovalStateDto> stateDtoHttpEntity = new HttpEntity<>(stateDto);

        ResponseEntity<Long> approvalEntity = restTemplate.exchange(url, HttpMethod.PUT, stateDtoHttpEntity, Long.class);

        assertThat(approvalEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(approvalEntity.getBody()).isGreaterThan(5L);

        List<ApprovalEntity> all = approvalRepository.findAll();
        assertThat(all.get(5).getApprovalState()).isEqualTo(expectedState);
    }

    @Test
    public void ApprovalUpadate() throws Exception {
        ApprovalEntity update = approvalRepository.save(ApprovalEntity.builder()
                .approvalName("test")
                .formEntity(FormEntity.builder().formNo(1L).build())
                .masterLineEntity(MasterLineEntity.builder().lineNo(1L).build())
                .docboxEntity(DocboxEntity.builder().docboxNo(1L).build())
                .approContent("컨트롤러기안")
                .memberEntity(MemberEntity.builder().userNo(1L).build())
                .approvalState(0)
                .regdate(new Date())
                .build());

        Long updateId = update.getApprovalNo();
        String expectedName = "내용변경테스트";
        Long expectedLine = 3L;
        Long expectedDocbox = 1L;
        String expectedContent ="컨트롤러기안내용변경테스트기안";
        ApprovalUpdateDto updateDto = ApprovalUpdateDto.builder()
                .approvalName(expectedName)
                .lineNo(expectedLine)
                .docboxNo(expectedDocbox)
                .approContent(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/approval/" + updateId;

        HttpEntity<ApprovalUpdateDto>updateDtoHttpEntity = new HttpEntity<>(updateDto);

        ResponseEntity<Long> approvalEntity = restTemplate.exchange(url, HttpMethod.PUT, updateDtoHttpEntity, Long.class);

        assertThat(approvalEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(approvalEntity.getBody()).isGreaterThan(7L);

        List<ApprovalEntity> all = approvalRepository.findAll();
        assertThat(all.get(7).getApprovalName()).isEqualTo(expectedName);
        assertThat(all.get(7).getMasterLineEntity().getLineNo()).isEqualTo(expectedLine);
        assertThat(all.get(7).getDocboxEntity().getDocboxNo()).isEqualTo(expectedDocbox);
        assertThat(all.get(7).getApproContent()).isEqualTo(expectedContent);
    }


}
