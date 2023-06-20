package com.ohalfmoon.firework.dto.todo;

import com.ohalfmoon.firework.model.DeptEntity;
import com.ohalfmoon.firework.model.MemberEntity;
import com.ohalfmoon.firework.model.TodoEntity;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TodoListByDeptDTO {
    private LocalDateTime todoStart;
    private LocalDateTime todoFinish;
    private String todoTitle;
    private String todoDetail;
    private Long deptNo;

    public TodoListByDeptDTO(final TodoEntity entity) {
        todoStart = entity.getTodoStart();
        todoFinish = entity.getTodoFinish();
        todoTitle = entity.getTodoTitle();
        todoDetail = entity.getTodoDetail();
        deptNo = entity.getMemberEntity().getDeptEntity().getDeptNo();
    }

    public TodoEntity toEntity() {
        return TodoEntity.builder()
                .todoStart(todoStart)
                .todoFinish(todoFinish)
                .todoTitle(todoTitle)
                .todoDetail(todoDetail)
                .memberEntity(MemberEntity.builder()
                        .deptEntity(DeptEntity.builder()
                                .deptNo(deptNo)
                                .build())
                        .build())
                .build();
    }
}
