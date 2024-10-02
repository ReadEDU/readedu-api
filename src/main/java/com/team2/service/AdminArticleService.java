package com.team2.service;

import com.team2.model.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminArticleService {
    List<Article> findAll();
    Page<Article> paginate(Pageable pageable);
    Article create(Article article);
    Article findById(Integer id);
    Article update(Integer id, Article updateArticle);
    void delete(Integer id);
}
