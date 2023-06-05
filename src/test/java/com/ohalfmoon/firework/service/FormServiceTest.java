package com.ohalfmoon.firework.service;

import com.ohalfmoon.firework.dto.form.FormResponseDto;
import com.ohalfmoon.firework.dto.form.FormSaveDto;
import com.ohalfmoon.firework.dto.form.FormUpdateDto;
import com.ohalfmoon.firework.model.FormEntity;
import com.ohalfmoon.firework.repository.FormRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * packageName    : com.ohalfmoon.firework.service
 * fileName       : FormServiceTest
 * author         : 방한솔
 * date           : 2023/06/05
 * description    : 결재양식 Service 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/05        방한솔           최초 생성
 */
@SpringBootTest
class FormServiceTest {
    @Autowired
    private FormService formService;

    @Autowired
    private FormRepository formRepository;

    /**
     * 폼 dto 저장 테스트
     */
    @Test
    @DisplayName("폼 dto 저장 테스트")
    void save() {
        FormSaveDto formSaveDto = FormSaveDto.builder()
                .formName("테스트이름1")
                .formText("테스트글자1")
                .isUsed(false)
                .build();

        Long saveId = formService.save(formSaveDto);

        FormEntity formEntity = formRepository.findById(saveId).orElse(null);

        Assertions.assertThat(formEntity).isNotNull();
    }

    /**
     * 폼 단일 조회 테스트 및 단일조회 실패시 익셉션 테스트
     */
    @Test
    @DisplayName("폼 단일 조회 테스트")
    void findByFormNo() {
        final Long exceptionNo = 0L;

        Assertions.assertThatThrownBy(()->formService.findByFormNo(exceptionNo))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 양식이 존재하지 않습니다. formNo = " + exceptionNo);

        Long formNo = 6L;
        FormResponseDto byFormNo = formService.findByFormNo(formNo);

        FormResponseDto formResponseDto = new FormResponseDto(formRepository.findById(formNo).orElse(new FormEntity()));

        Assertions.assertThat(byFormNo).isEqualTo(formResponseDto);
    }

    /**
     * 폼 수정 테스트 및 단일조회 실패시 익셉션 테스트
     */
    @Test
    @DisplayName("폼 수정 테스트")
    void update() {
        final Long exceptionNo = 0L;

        FormUpdateDto updateDto = FormUpdateDto.builder()
                .formName("수정 이름 테스트")
                .formText("수정 양식 테스트")
                .isUsed(true)
                .build();

        Assertions.assertThatThrownBy(()->formService.update(exceptionNo, updateDto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 양식이 존재하지 않습니다. formNo = " + exceptionNo);

        Long formNo = 6L;
        FormEntity formEntity = formRepository.findById(formNo).orElse(null);

        Long updateFormNo = formService.update(formNo, updateDto);
        FormEntity updateformEntity = formRepository.findById(updateFormNo).orElse(null);

        Assertions.assertThat(formEntity).isEqualTo(updateformEntity);
    }

    /**
     * 폼 삭제 테스트 및 단일조회 실패시 익셉션 테스트
     */
    @Test
    @DisplayName("폼 삭제 테스트")
    void delete() {
        final Long exceptionNo = 0L;

        Assertions.assertThatThrownBy(()->formService.delete(exceptionNo))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 양식이 존재하지 않습니다. formNo = " + exceptionNo);

        Long formNo = 10L;

        formService.delete(formNo);

        Assertions.assertThatThrownBy(()->formService.findByFormNo(formNo))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 양식이 존재하지 않습니다. formNo = " + formNo);

    }
}