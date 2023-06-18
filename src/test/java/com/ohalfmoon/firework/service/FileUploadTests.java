package com.ohalfmoon.firework.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : FileUploadTests
 * author         : banghansol
 * date           : 2023/06/16
 * description    : 파일업로드 저장 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/16        banghansol       최초 생성
 */
@SpringBootTest
@Slf4j
public class FileUploadTests {
    @Autowired
    ResourceLoader resourceLoader;
    @Test
    public void pathTests() throws IOException {
        String uploadDir = "/upload";
        String filePath = "/37fc0318-2aa7-4972-95ed-be6bfa10b23a.jpeg";

        String projectPath = new File("").getAbsolutePath();

        File uploadFolder = new File(projectPath + uploadDir);

        if(!uploadFolder.exists()){
            uploadFolder.mkdirs();
            log.info("업로드 폴더 생성");
        }
    }
}
