package com.ohalfmoon.firework.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * packageName    : com.ohalfmoon.firework.controller
 * fileName       : AttachControllerTests
 * author         : banghansol
 * date           : 2023/06/19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/19        banghansol       최초 생성
 */
@SpringBootTest
//@WebMvcTest(controllers = AttachUploadController.class)
public class AttachControllerTests {
    @Autowired
    WebApplicationContext webApplicationContext;

    @Test
    @Transactional
    public void uploadTest() throws Exception {
        byte[] imageData = "Hello, World!".getBytes(StandardCharsets.UTF_8);

        MockMultipartFile file = new MockMultipartFile(
                "upload"
                , "hello.png"
                , MimeTypeUtils.IMAGE_JPEG_VALUE
                , new ByteArrayInputStream(imageData)
        );

        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload/ajax").file(file))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Transactional
    public void getTest() throws Exception {
        MockMvc mockMvc
                = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Resource resource = new UrlResource("file:" + new File("").getAbsolutePath()
                + "336bbff0-b9e9-44dc-836d-45f86fad8168.png");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/upload/imageView")
                        .param("fileNo", "14")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.header().string(HttpHeaders.CONTENT_DISPOSITION, "inline"))
                .andExpect(MockMvcResultMatchers.header().string(HttpHeaders.CONTENT_TYPE, MediaType.IMAGE_PNG_VALUE))
                .andReturn();
    }
}
