package com.team2.dto.article;

import lombok.Data;

@Data
public class ArticleDetailsDTO {
    private Integer id;
    private String title;
    private String slug;
    private String content;
    private Integer categoryId;
    private Integer authorId;
}
