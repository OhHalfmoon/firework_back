package com.ohalfmoon.firework.dto.todo;

import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.TodoEntity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TodoSaveDTO {
    private Boolean isHoliday;
    private LocalDateTime todoStart;
    private LocalDateTime todoFinish;
    private String todoTitle;
    private String todoDetail;
    private Long userNo;

    public TodoSaveDTO(final TodoEntity entity) {
        isHoliday = entity.isHoliday();
        todoStart = entity.getTodoStart();
        todoFinish = entity.getTodoFinish();
        todoTitle = entity.getTodoTitle();
        todoDetail = entity.getTodoDetail();
    }

   public TodoEntity toEntity() {
        return TodoEntity.builder()
                    .holiday(isHoliday)
                    .todoStart(todoStart)
                    .todoFinish(todoFinish)
                    .todoTitle(todoTitle)
                    .todoDetail(todoDetail)
                    .memberEntity(MemberEntity.builder()
                        .userNo(userNo)
                    .build())
                    .build();
    }
}
