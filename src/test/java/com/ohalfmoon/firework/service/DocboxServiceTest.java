package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.docbox.DocboxListResponseDTO;
import com.ohalfmoon.firework.persistence.DocboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class DocboxServiceTest {
    @Autowired
    private DocboxService docboxService;

    @Autowired
    DocboxRepository docboxRepository;

    @Test
    @DisplayName("문서함 리스트 조회 테스트")
    void getListTest() {
        List<DocboxListResponseDTO> listDto = docboxService.docboxList();
        log.info("{}", listDto);
    }
}
