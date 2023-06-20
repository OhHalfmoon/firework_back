package com.ohalfmoon.firework.dto.todo;

import com.ohalfmoon.firework.model.TodoEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TodoResponseDTO {
    private LocalDateTime todoStart;
    private LocalDateTime todoFinish;
    private String todoTitle;
    private String todoDetail;

    public TodoResponseDTO(final TodoEntity entity) {
        todoStart = entity.getTodoStart();
        todoFinish = entity.getTodoFinish();
        todoTitle = entity.getTodoTitle();
        todoDetail = entity.getTodoDetail();
    }

    public TodoEntity toEntity(){
        return TodoEntity.builder()
                .todoStart(todoStart)
                .todoFinish(todoFinish)
                .todoTitle(todoTitle)
                .todoDetail(todoDetail)
                .build();
    }
}
