package com.ohalfmoon.firework.dto.todo;

import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.TodoEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TodoListByUserDTO {
    private LocalDateTime todoStart;
    private LocalDateTime todoFinish;
    private String todoTitle;
    private String todoDetail;
    private Boolean isHoliday;
    private Long userNo;

    public TodoListByUserDTO(TodoEntity entity) {
        todoStart = entity.getTodoStart();
        todoFinish = entity.getTodoFinish();
        todoTitle = entity.getTodoTitle();
        todoDetail = entity.getTodoDetail();
        isHoliday = entity.isHoliday();
        userNo = entity.getMemberEntity().getUserNo();
    }

    public TodoEntity toEntity() {
        return TodoEntity.builder()
                        .todoStart(todoStart)
                        .todoFinish(todoFinish)
                        .todoTitle(todoTitle)
                        .todoDetail(todoDetail)
                        .holiday(isHoliday)
                        .memberEntity(MemberEntity.builder()
                            .userNo(userNo)
                        .build())
                        .build();
    }
}
