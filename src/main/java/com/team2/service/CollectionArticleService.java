package com.team2.service;

import com.team2.model.entity.Article;
import com.team2.model.entity.CollectionArticle;

import java.util.List;

public interface CollectionArticleService {

    CollectionArticle addArticleToFavorite(Integer articleId, Integer favoriteId);
    void removeArticleFromFavorite(Integer articleId, Integer favoriteId);
    List<Article> getArticlesInFavorite(Integer favoriteId);
}