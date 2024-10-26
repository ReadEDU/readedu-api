package com.team2.service.impl;

import com.team2.model.entity.Article;
import com.team2.model.entity.Author;
import com.team2.model.entity.Category;
import com.team2.repository.ArticleRepository;
import com.team2.repository.AuthorRepository;
import com.team2.repository.CategoryRepository;
import com.team2.service.AdminArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminArticleServiceImpl implements AdminArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Article> paginate(Pageable pageable) {
        return articleRepository.findAll(pageable);
    }

    @Override
    public Article create(Article article) {
        Category category = categoryRepository.findById(article.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + article.getCategory().getId()));
        Author author = authorRepository.findById(article.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + article.getAuthor().getId()));

        article.setCategory(category);
        article.setAuthor(author);
        article.setCreatedAt(LocalDateTime.now());

        return articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    @Override
    public Article findById(Integer id) {
        return articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
    }

    @Transactional
    @Override
    public Article update(Integer id, Article updateArticle) {
        Article articleFromDb = findById(id);

        Category category = categoryRepository.findById(updateArticle.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + updateArticle.getCategory().getId()));
        Author author = authorRepository.findById(updateArticle.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + updateArticle.getAuthor().getId()));

        articleFromDb.setTitle(updateArticle.getTitle());
        articleFromDb.setSlug(updateArticle.getSlug());
        articleFromDb.setContent(updateArticle.getContent());

        articleFromDb.setCategory(category);
        articleFromDb.setAuthor(author);
        articleFromDb.setUpdatedAt(LocalDateTime.now());

        return articleRepository.save(articleFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
        articleRepository.delete(article);
    }
}
