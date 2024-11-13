package com.team2.api;

import com.team2.dto.article.ArticleCreateUpdateDTO;
import com.team2.dto.article.ArticleDetailsDTO;
import com.team2.service.AdminArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/articles")
@PreAuthorize("hasAnyRole('ADMIN','CREATOR')")
public class AdminArticleController {
    private final AdminArticleService adminArticleService;

    @GetMapping
    public ResponseEntity<List<ArticleDetailsDTO>> list(){
        List<ArticleDetailsDTO> articles = adminArticleService.findAll();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ArticleDetailsDTO>> paginate(
            @PageableDefault(size = 5, sort = "title") Pageable pageable){
        Page<ArticleDetailsDTO> page = adminArticleService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ArticleDetailsDTO> create(@Valid @RequestBody ArticleCreateUpdateDTO articleFormDTO) {
        ArticleDetailsDTO createdArticle = adminArticleService.create(articleFormDTO);
        return new ResponseEntity<>(createdArticle, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDetailsDTO> get(@PathVariable Integer id){
        ArticleDetailsDTO article = adminArticleService.findById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDetailsDTO> update(@PathVariable Integer id, @Valid @RequestBody ArticleCreateUpdateDTO articleFormDTO) {
        ArticleDetailsDTO updateArticle = adminArticleService.update(id, articleFormDTO);
        return new ResponseEntity<>(updateArticle, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        adminArticleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}