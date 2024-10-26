package com.team2.service.impl;


import com.team2.model.entity.Article;
import com.team2.model.entity.CollectionArticle;
import com.team2.model.entity.Favorite;
import com.team2.repository.CollectionArticleRepository;
import com.team2.repository.FavoriteRepository;
import com.team2.service.CollectionArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionArticleServiceImpl implements CollectionArticleService {
    private final CollectionArticleRepository collectionArticleRepository;
    private final FavoriteRepository favoriteRepository;

    @Override
    public CollectionArticle addArticleToFavorite(Integer articleId, Integer favoriteId) {
        LocalDateTime addedDate = LocalDateTime.now();
        collectionArticleRepository.addArticleToFavorite(articleId, favoriteId, addedDate);

        CollectionArticle collectionArticle = new CollectionArticle();
        collectionArticle.setArticle(articleId);
        collectionArticle.setFavorite(favoriteId);
        collectionArticle.setAddedDate(addedDate);
        return collectionArticle;
    }

    @Override
    public void removeArticleFromFavorite(Integer articleId, Integer favoriteId) {
        collectionArticleRepository.deleteByArticleAndFavorite(articleId, favoriteId);
    }

    @Override
    public List<Article> getArticlesInFavorite(Integer favoriteId) {
        Favorite favorite = favoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new RuntimeException("Favorite not found"));
        return collectionArticleRepository.findArticlesByFavorite(favorite);
    }
}
