package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.FormResponseDto;
import com.ohalfmoon.firework.dto.FormSaveDto;
import com.ohalfmoon.firework.dto.FormUpdateDto;
import com.ohalfmoon.firework.model.FormEntity;
import com.ohalfmoon.firework.repository.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : FormService
 * author         : 방한솔
 * date           : 2023/06/05
 * description    : 결재양식 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/05        방한솔           최초 생성
 */
@Service
@RequiredArgsConstructor
public class FormService {
    private final FormRepository formRepository;

    @Transactional
    public Long save(FormSaveDto form) {
        return formRepository.save(form.toEntity()).getFormNo();
    }

    public FormResponseDto findByFormNo(Long formNo) {
        FormEntity formEntity = formRepository
                .findById(formNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 양식이 존재하지 않습니다. formNo = " + formNo));

        return new FormResponseDto(formEntity);
    }

    @Transactional
    public Long update(Long formNo, FormUpdateDto form) {
        FormEntity formEntity = formRepository.findById(formNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 양식이 존재하지 않습니다. formNo = " + formNo));

        formEntity.update(formNo, form.getFormName(), form.getFormText(), form.getIsUsed());

        return formNo;
    }

    @Transactional
    public void delete(Long formNo) {
        FormEntity formEntity = formRepository.findById(formNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 양식이 존재하지 않습니다. formNo = " + formNo));

        formRepository.delete(formEntity);
    }

    public Page<FormResponseDto> searchFormList(Optional<String> formName, Pageable pageRequest) {
        Page<FormEntity> formEntities = formRepository.findByFormNameContaining(formName.orElse(""), pageRequest);

        return formEntities.map(item -> new FormResponseDto(item));
    }
}
