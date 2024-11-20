package com.team2.service;

import com.team2.dto.article.ArticleDetailsDTO;
import java.util.List;
public interface ArticleService {
    List<ArticleDetailsDTO> findTop6ArticlesByCreatedAt();
}