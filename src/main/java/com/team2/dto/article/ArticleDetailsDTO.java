package com.team2.dto.article;

import lombok.Data;

@Data
public class ArticleDetailsDTO {
    private Integer id;
    private String title;
    private String slug;
    private String content;
    private String filePath;
    private String categoryName;
    private String creatorName;
}
