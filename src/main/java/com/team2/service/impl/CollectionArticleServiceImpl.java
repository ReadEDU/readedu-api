package com.team2.service.impl;


import com.team2.exception.BadRequestException;
import com.team2.model.entity.Article;
import com.team2.model.entity.CollectionArticle;
import com.team2.model.entity.Favorite;
import com.team2.repository.CollectionArticleRepository;
import com.team2.repository.FavoriteRepository;
import com.team2.service.CollectionArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionArticleServiceImpl implements CollectionArticleService {

    private final CollectionArticleRepository collectionArticleRepository;
    private final FavoriteRepository favoriteRepository; // Asegúrate de tener acceso al repo de Collection

    @Override
    @Transactional
    public CollectionArticle addArticleToFavorite(Integer articleId, Integer favoriteId) {
        // Verificar si el libro ya está en la colección
        if (collectionArticleRepository.existsByArticleAndFavorite(articleId, favoriteId)) {
            throw new BadRequestException("Este articulo ya está en tus favoritos.");
        }

        // Establecer la fecha en que se agrega el libro
        LocalDateTime addedDate = LocalDateTime.now();

        // Agregar el libro a la colección
        collectionArticleRepository.addArticleToFavorite(articleId, favoriteId, addedDate);

        // Crear el objeto para devolver
        CollectionArticle collectionArticle = new CollectionArticle();
        collectionArticle.setArticle(articleId);
        collectionArticle.setFavorite(favoriteId);
        collectionArticle.setAddedDate(addedDate);

        return collectionArticle;
    }

    @Override
    @Transactional
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