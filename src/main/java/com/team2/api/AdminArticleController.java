package com.team2.api;

import com.team2.model.entity.Article;
import com.team2.service.AdminArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/articles")
public class AdminArticleController {
    private final AdminArticleService adminArticleService;

    @GetMapping("/list")
    public ResponseEntity<List<Article>> list(){
        List<Article> articles = adminArticleService.findAll();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Article>> paginate(@PageableDefault(size = 5, sort = "title") Pageable pageable){
        Page<Article> page = adminArticleService.paginate(pageable);
        return new ResponseEntity<>(page, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Article> create(@RequestBody Article article){
        Article createdArticle = adminArticleService.create(article);
        return new ResponseEntity<>(createdArticle, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> get(@PathVariable Integer id){
        Article article = adminArticleService.findById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> update(@PathVariable Integer id, @RequestBody Article article){
        Article updatedArticle = adminArticleService.update(id, article);
        return new ResponseEntity<>(updatedArticle, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        adminArticleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
