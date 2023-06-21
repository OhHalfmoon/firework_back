package com.ohalfmoon.firework.model.spec;

import com.ohalfmoon.firework.model.FormEntity;
import com.ohalfmoon.firework.model.FormEntity_;
import org.springframework.data.jpa.domain.Specification;

/**
 * packageName    : com.ohalfmoon.firework.model.spec
 * fileName       : FormSpec
 * author         : banghansol
 * date           : 2023/06/20
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/20        banghansol       최초 생성
 */
public class FormSpec {
    public static Specification<FormEntity> formSearch(String type, String keyword) {
        if(keyword == null) return null;

        switch (type) {
            case "T" : return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get(FormEntity_.FORM_NAME), "%"+keyword+"%"));

            case "C" : return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get(FormEntity_.FORM_TEXT), "%"+keyword+"%"));

            case "TC" : return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.get(FormEntity_.FORM_NAME), "%"+keyword+"%"),
                            criteriaBuilder.like(root.get(FormEntity_.FORM_TEXT), "%"+keyword+"%")
                    ));

            default: return null;
        }
    }
}
