package com.team2.dto.article;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleDetailsDTO {
    private Integer id;
    private String title;
    private String slug;
    private String content;
    private String coverPath;
    private String filePath;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String categoryName;
    private String creatorName;
}
