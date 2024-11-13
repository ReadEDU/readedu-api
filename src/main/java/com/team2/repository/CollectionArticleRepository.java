package com.team2.repository;

import com.team2.model.entity.Article;
import com.team2.model.entity.CollectionArticle;
import com.team2.model.entity.CollectionArticlePK;
import com.team2.model.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface CollectionArticleRepository extends JpaRepository<CollectionArticle, CollectionArticlePK> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO collection_articles (article_id, favorite_id, added_date) VALUES (:articleId, :favoriteId, :addedDate)", nativeQuery = true)
    void addArticleToFavorite(@Param("articleId") Integer articleId, @Param("favoriteId") Integer favoriteId, @Param("addedDate") LocalDateTime addedDate);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM collection_articles WHERE article_id = :articleId AND favorite_id = :favoriteId", nativeQuery = true)
    void deleteByArticleAndFavorite(@Param("articleId") Integer articleId, @Param("favoriteId") Integer favoriteId);

    @Query("SELECT cb.article FROM CollectionArticle cb WHERE cb.favorite = :favorite")
    List<Article> findArticlesByFavorite(@Param("favorite") Favorite favorite);

    @Query(value = "SELECT COUNT(*) > 0 FROM collection_articles WHERE article_id = :articleId AND favorite_id = :favoriteId", nativeQuery = true)
    boolean existsByArticleAndFavorite(@Param("articleId") Integer articleId, @Param("favoriteId") Integer favoriteId);
}