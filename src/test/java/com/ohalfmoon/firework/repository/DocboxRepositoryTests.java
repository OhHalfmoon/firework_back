package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.DocboxEntity;
import com.ohalfmoon.firework.persistence.DocboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * packageName :  com.ohalfmoon.firework.repository
 * fileName : DocboxRepositoryTests
 * author :  오상현
 * date : 2023-06-13
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-13           오상현               최초 생성
 */
@SpringBootTest
@Slf4j
public class DocboxRepositoryTests {

    @Autowired
    DocboxRepository docboxRepository;

    @Test
    public void createTests() {
        docboxRepository.save(DocboxEntity.builder()
                .docboxName("인사팀 문서함")
                .build());
    }
    @Test
    public void docboxListTests() {
        List<DocboxEntity> docList = docboxRepository.findAll();
        log.info("{}", docList);
    }

    @Test
    public  void removeTests() {
        docboxRepository.delete(DocboxEntity.builder().docboxNo(2L).build());
    }
}
