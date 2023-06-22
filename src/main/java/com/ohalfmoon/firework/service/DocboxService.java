package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.docbox.DocboxListResponseDTO;
import com.ohalfmoon.firework.persistence.DocboxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName :  com.ohalfmoon.firework.service
 * fileName : DocboxService
 * author :  오상현
 * date : 2023-06-13
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-13           오상현                최초 생성
 */
@Service
public class DocboxService {

    @Autowired
    private DocboxRepository docboxRepository;

    @Transactional(readOnly = true)
    public List<DocboxListResponseDTO> docboxList() {
        return docboxRepository.findAll().stream()
                .map(DocboxListResponseDTO::new)
                .collect(Collectors.toList());
    }

}
