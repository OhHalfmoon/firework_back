package com.ohalfmoon.firework.dto.todo;

import com.ohalfmoon.firework.model.TodoEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TodoListByHolidayDTO {
    private LocalDateTime todoStart;
    private LocalDateTime todoFinish;
    private String todoTitle;
    private String todoDetail;
    private Boolean isHoliday;

    public TodoListByHolidayDTO(TodoEntity entity) {
        todoStart = entity.getTodoStart();
        todoFinish = entity.getTodoFinish();
        todoTitle = entity.getTodoTitle();
        todoDetail = entity.getTodoDetail();
        isHoliday = entity.isHoliday();
    }

    public TodoEntity todoEntity(){
        return TodoEntity.builder()
                .todoStart(todoStart)
                .todoFinish(todoFinish)
                .todoTitle(todoTitle)
                .todoDetail(todoDetail)
                .build();
    }
}