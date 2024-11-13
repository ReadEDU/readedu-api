package com.team2.dto.favorite;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavoriteDetailsDTO {

    private Integer id;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String readerName;
}
