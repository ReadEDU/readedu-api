package com.team2.api;

import com.team2.dto.article.ArticleDetailsDTO;
import com.team2.mapper.ArticleMapper;
import com.team2.model.entity.Article;
import com.team2.model.entity.CollectionArticle;
import com.team2.service.CollectionArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/collections-articles")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('READER' , 'ADMIN')")
public class CollectionArticleController {
    private final CollectionArticleService collectionArticleService;
    private final ArticleMapper articleMapper;

    @PostMapping("/{favoriteId}/add-article")
    public ResponseEntity<CollectionArticle> addArticleToFavorite(@PathVariable Integer favoriteId, @RequestParam Integer articleId) {
        CollectionArticle collectionArticle = collectionArticleService.addArticleToFavorite(articleId, favoriteId);
        return new ResponseEntity<>(collectionArticle, HttpStatus.CREATED);
    }

    @DeleteMapping("/{favoriteId}/remove-article/{articleId}")
    public ResponseEntity<Void> removeArticleFromFavorite(@PathVariable Integer favoriteId, @PathVariable Integer articleId) {
        collectionArticleService.removeArticleFromFavorite(articleId, favoriteId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/{favoriteId}/articles")
    public ResponseEntity<List<ArticleDetailsDTO>> getArticlesInFavorite(@PathVariable Integer favoriteId) {
        List<Article> articles = collectionArticleService.getArticlesInFavorite(favoriteId);
        List<ArticleDetailsDTO> articleDetailsDTOList = articles.stream()
                .map(articleMapper::toDetailsDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(articleDetailsDTOList);
    }

}