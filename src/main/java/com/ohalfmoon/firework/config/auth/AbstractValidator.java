package com.ohalfmoon.firework.config.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * packageName :  com.ohalfmoon.firework.config.auth
 * fileName : AbstractValidator
 * author :  ycy
 * date : 2023-06-20
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2023-06-20                ycy             최초 생성
 */
@Slf4j
public abstract class AbstractValidator<T> implements Validator {

    // Validated는 검증기를 실행하라는 애노테이션
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @SuppressWarnings("unchecked") // 컴파일러 경고 x
    @Override
    public void validate(Object target, Errors errors) {
        try {
            doValidate((T) target, errors);
        } catch (IllegalStateException e) {
            log.error("중복 검증 에러", e);
            throw e;
        }
    }

    protected abstract void doValidate(final T dto, final Errors errors);
}
