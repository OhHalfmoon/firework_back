package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.fileUpload.AttachResponseDto;
import com.ohalfmoon.firework.dto.fileUpload.AttachSaveDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : AttachServiceTests
 * author         : banghansol
 * date           : 2023/06/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/19        banghansol       최초 생성
 */
@SpringBootTest
@Slf4j
public class AttachServiceTests {
    @Autowired
    private AttachService attachService;

    @Transactional()
    @Test
    public void saveTest() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Hello, World!".getBytes());

        AttachSaveDto dto = AttachSaveDto.builder()
                .uuid(UUID.randomUUID().toString())
                .ext(FilenameUtils.getExtension(file.getOriginalFilename()))
                .originName(file.getOriginalFilename())
                .approvalNo(32L)
                .file(file)
                .build();

        attachService.fileSave(dto);

        AttachSaveDto dto1 = AttachSaveDto.builder()
                .uuid(UUID.randomUUID().toString())
                .ext(FilenameUtils.getExtension(file.getOriginalFilename()))
                .originName(file.getOriginalFilename())
                .approvalNo(32L)
                .file(file)
                .build();

        attachService.fileSave(dto1);
    }

    @Test
    @Transactional(readOnly = true)
    public void getFileListByApprovalNo(){
        List<AttachResponseDto> fileList = attachService.getFileList(32L);

        log.info("fileList : {}", fileList);
    }

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void deleteTests() throws IOException {
        Long deleteAll = attachService.deleteAllApprovalNo(110L);

        Assertions.assertThat(deleteAll).isGreaterThan(0L);
    }
}
