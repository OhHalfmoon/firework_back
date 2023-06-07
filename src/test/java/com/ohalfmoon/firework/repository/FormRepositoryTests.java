package com.ohalfmoon.firework.repository;

import com.ohalfmoon.firework.model.FormEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * packageName    : com.ohalfmoon.firework.form
 * fileName       : FormRepositoryTests
 * author         : 방한솔
 * date           : 2023/06/05
 * description    : 결재양식 Repository 테스트
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/05        방한솔            최초 생성
 */
@SpringBootTest
public class FormRepositoryTests {
    @Autowired
    private FormRepository formRepository;

    /**
     * 결제양식 저장 테스트
     */
    @Test
    public void formSave() {
        LocalDateTime now = LocalDateTime.now();

        FormEntity input = FormEntity.builder()
                .formName("테스트 네임")
                .formText("테스트 텍스트")
                .isUsed(true)
                .build();

        FormEntity save = formRepository.save(input);

        FormEntity findForm = formRepository.findById(save.getFormNo()).orElse(null);

        Assertions.assertThat(save).isEqualTo(findForm);
        Assertions.assertThat(save.getRegdate()).isAfter(now);
        Assertions.assertThat(save.getUpdatedate()).isAfter(now);
    }

    /**
     * PK로 결제양식 조회 테스트
     */
    @Test
    public void findOne() {
        Long formNo = 6L;

        FormEntity findForm = formRepository.findById(formNo).orElse(null);

        Assertions.assertThat(findForm).isNotNull();
    }

    /**
     * 폼 이름, 폼 내용, 사용여부 변경 테스트
     */
    @Test
    public void updateForm() {
        Long formNo = 6L;

        FormEntity form = formRepository.findById(formNo).orElse(null);

        Objects.requireNonNull(form, "값을 찾을 수 없습니다. formNo를 확인해주세요")
                .update(formNo, "변경 폼 이름", "변경 폼 내용", false);

        FormEntity save = formRepository.save(form);

        Assertions.assertThat(form).isEqualTo(save);
    }

    /**
     * 결재양식 삭제 테스트
     */
    @Test
    public void deleteForm() {
        Long formNo = 7L;

        formRepository.deleteById(formNo);

        FormEntity searchForm = formRepository.findById(formNo).orElse(null);

        Assertions.assertThat(searchForm).isNull();
    }
}
