package com.team2.api;

import com.team2.dto.article.ArticleDetailsDTO;
import com.team2.service.AdminArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class HomeArticleController {

    private final AdminArticleService adminArticleService;

    @GetMapping("/recent")
    public ResponseEntity<List<ArticleDetailsDTO>> getRecentArticles() {
        List<ArticleDetailsDTO> recentArticles = adminArticleService.findTop6ArticlesByCreatedAt();
        return new ResponseEntity<>(recentArticles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDetailsDTO> get(@PathVariable Integer id){
        ArticleDetailsDTO article = adminArticleService.findById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }
}
