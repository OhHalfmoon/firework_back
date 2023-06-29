package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.fileUpload.AttachResponseDto;
import com.ohalfmoon.firework.dto.fileUpload.AttachDto;
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

        AttachDto dto = new AttachDto(file);

        attachService.upload(file, dto);

        AttachDto dto1 = new AttachDto(file);

        attachService.upload(file, dto1);
    }

    @Test
    @Transactional(readOnly = true)
    public void getFileListByApprovalNo(){
        List<AttachResponseDto> fileList = attachService.getFileList(32L);

        log.info("fileList : {}", fileList);
    }

    @Test
    @Transactional(readOnly = true)
    public void getFileListByBoardNo(){
        List<AttachResponseDto> fileList = attachService.getBoardFileList(334L);

        log.info("fileList : {}", fileList);
    }

}
