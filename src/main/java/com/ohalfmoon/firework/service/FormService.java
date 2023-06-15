package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.FormResponseDto;
import com.ohalfmoon.firework.dto.FormSaveDto;
import com.ohalfmoon.firework.dto.FormUpdateDto;
import com.ohalfmoon.firework.model.FormEntity;
import com.ohalfmoon.firework.model.FormEntity_;
import com.ohalfmoon.firework.persistence.FormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    /**
     * Save long.
     *
     * @param form the form
     * @return the long
     */
    @Transactional
    public Long save(FormSaveDto form) {
        return formRepository.save(form.toEntity()).getFormNo();
    }

    /**
     * Find by form no form response dto.
     *
     * @param formNo the form no
     * @return the form response dto
     */
    public FormResponseDto findByFormNo(Long formNo) {
        FormEntity formEntity = formRepository
                .findById(formNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 양식이 존재하지 않습니다. formNo = " + formNo));

        return new FormResponseDto(formEntity);
    }

    /**
     * Update long.
     *
     * @param formNo the form no
     * @param form   the form
     * @return the long
     */
    @Transactional
    public Long update(Long formNo, FormUpdateDto form) {
        FormEntity formEntity = formRepository.findById(formNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 양식이 존재하지 않습니다. formNo = " + formNo));

        formEntity.update(formNo, form.getFormName(), form.getFormText(), form.getIsUsed());

        return formNo;
    }

    /**
     * Delete.
     *
     * @param formNo the form no
     */
    @Transactional
    public void delete(Long formNo) {
        FormEntity formEntity = formRepository.findById(formNo)
                .orElseThrow(() -> new IllegalArgumentException("해당 양식이 존재하지 않습니다. formNo = " + formNo));

        formRepository.delete(formEntity);
    }


    /**
     * Search form list page.
     *
     * @param formName    the form name
     * @param pageRequest the page request
     * @return the page
     */
    public Page<FormEntity> searchFormList(Optional<String> formName, Pageable pageRequest) {


        //Page<FormEntity> all = formRepository.findAll(nameLike(formName.orElse("")), pageRequest);

        //return formRepository.findByFormNameContaining(formName.orElse(""), pageRequest);

        return formRepository.findAll(nameLike(formName.orElse("")), pageRequest);
    }

    /**
     * Gets form list.
     *
     * @return the form list
     */
    public List<FormResponseDto> getFormList() {
        List<FormEntity> formEntityList = formRepository.findAll();

        return formEntityList.stream().map(FormResponseDto::new).collect(Collectors.toList());
    }

    private Specification<FormEntity> nameLike(String name) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get(FormEntity_.FORM_NAME), "%"+name+"%"));
    }
}
