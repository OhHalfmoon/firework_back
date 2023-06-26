package com.ohalfmoon.firework.model.spec;

import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.MemberEntity_;
import com.ohalfmoon.firework.model.MessageEntity;
import com.ohalfmoon.firework.model.MessageEntity_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;

/**
 * packageName    : com.ohalfmoon.firework.model.spec
 * fileName       : MessageSpec
 * author         : 우성준
 * date           : 2023/06/23
 * description    : 쪽지 검색 기능
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023/06/23        우성준              최초 생성
 */
public class MessageSpec {
    public static Specification<MessageEntity> MessageSearch(String type, String keyword, Long who, Boolean division) {
        if (keyword == null) return null;

        if (division) {
            switch (type) {
                case "T":
                    return ((root, query, criteriaBuilder) ->
                            criteriaBuilder.and(
                                    criteriaBuilder.like(root.get(MessageEntity_.MESSAGE_TITLE), "%" + keyword + "%"),
                                    criteriaBuilder.equal(root.get(MessageEntity_.RECEIVER), who)
                            ));

                case "C":
                    return ((root, query, criteriaBuilder) ->
                            criteriaBuilder.and(
                                    criteriaBuilder.like(root.get(MessageEntity_.MESSAGE_CONTENT), "%" + keyword + "%"),
                                    criteriaBuilder.equal(root.get(MessageEntity_.RECEIVER), who)
                            ));
                default:
                    return null;
            }
        }
        else  {
            switch (type) {
                case "T":
                    return ((root, query, criteriaBuilder) ->
                            criteriaBuilder.and(
                                    criteriaBuilder.like(root.get(MessageEntity_.MESSAGE_TITLE), "%" + keyword + "%"),
                                    criteriaBuilder.equal(root.get(MessageEntity_.SENDER), who)
                            ));

                case "C":
                    return ((root, query, criteriaBuilder) ->
                            criteriaBuilder.and(
                                    criteriaBuilder.like(root.get(MessageEntity_.MESSAGE_CONTENT), "%" + keyword + "%"),
                                    criteriaBuilder.equal(root.get(MessageEntity_.SENDER), who)
                            ));
                default:
                    return null;
            }
        }
    }
}
