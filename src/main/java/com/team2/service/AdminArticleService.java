package com.team2.service;

import com.team2.dto.article.ArticleCreateUpdateDTO;
import com.team2.dto.article.ArticleDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminArticleService {
    List<ArticleDetailsDTO> findAll();
    Page<ArticleDetailsDTO> paginate(Pageable pageable);
    ArticleDetailsDTO findById(Integer id);
    ArticleDetailsDTO create(ArticleCreateUpdateDTO articleCreateUpdateDTO);
    ArticleDetailsDTO update(Integer id, ArticleCreateUpdateDTO updateArticleDTO);
    void delete(Integer id);
}
