package com.ohalfmoon.firework.dto;

import com.ohalfmoon.firework.dto.paging.PageRequestDTO;
import com.ohalfmoon.firework.dto.paging.PageResponseDTO;
import lombok.*;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class MessagePageRequestDto extends PageRequestDTO {
    private String name;
    private Boolean division;

    public MessagePageRequestDto(
            int page, int size, String type, String keyword,
            String name
            , Boolean division) {
        super(page, size, type, keyword);
        this.name = name;
        this.division = division;
    }
}
