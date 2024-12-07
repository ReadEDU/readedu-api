package com.team2.service.impl;

import com.team2.dto.article.ArticleCreateUpdateDTO;
import com.team2.dto.article.ArticleDetailsDTO;
import com.team2.exception.BadRequestException;
import com.team2.exception.ResourceNotFoundException;
import com.team2.mapper.ArticleMapper;
import com.team2.model.entity.Article;
import com.team2.model.entity.Creator;
import com.team2.model.entity.Category;
import com.team2.model.entity.User;
import com.team2.repository.ArticleRepository;
import com.team2.repository.CategoryRepository;
import com.team2.repository.CreatorRepository;
import com.team2.repository.UserRepository;
import com.team2.service.AdminArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminArticleServiceImpl implements AdminArticleService {
    private final ArticleRepository articleRepository;
    private final CategoryRepository categoryRepository;
    private final CreatorRepository creatorRepository;
    private final ArticleMapper articleMapper;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ArticleDetailsDTO> findAll() {
        return articleRepository.findAll()
                .stream()
                .map(articleMapper::toDetailsDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ArticleDetailsDTO> paginate(Pageable pageable) {
        return articleRepository.findAll(pageable)
                .map(articleMapper::toDetailsDTO);
    }

    @Transactional
    @Override
    public ArticleDetailsDTO create(ArticleCreateUpdateDTO articleCreateUpdateDTO) {
        articleRepository.findByTitleOrSlug(articleCreateUpdateDTO.getTitle(), articleCreateUpdateDTO.getSlug())
                .ifPresent(article -> {
                    throw new BadRequestException("Ya existe un articulo con el mismo titulo o slug");
                });

        // Asigna la categoria y el autor antes de guardar
        Category category = categoryRepository.findById(articleCreateUpdateDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con id: " + articleCreateUpdateDTO.getCategoryId()));
        User user = userRepository.findById(articleCreateUpdateDTO.getCreatorId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + articleCreateUpdateDTO.getCreatorId()));
        Optional<Creator> creator = creatorRepository.findByUser(user);
        if(creator.isEmpty()) {
            throw new ResourceNotFoundException("Creador no encontrado para el usuario con id: " + user.getId());
        }

        Article article = articleMapper.toEntity(articleCreateUpdateDTO);

        article.setCategory(category);
        article.setCreator(creator.get());
        article.setCreatedAt(LocalDateTime.now());

        return articleMapper.toDetailsDTO(articleRepository.save(article));
    }

    @Transactional(readOnly = true)
    @Override
    public ArticleDetailsDTO findById(Integer id) {
        Article article = articleRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Articulo no encontrado con el id: " + id));
        return articleMapper.toDetailsDTO(article);
    }

    @Transactional
    @Override
    public ArticleDetailsDTO update(Integer id,ArticleCreateUpdateDTO updateArticle) {
        Article articleFromDb = articleRepository.findById(id).
                    orElseThrow(() -> new ResourceNotFoundException("Articulo no encontrado con el id: " + id));

        // Asigna la categoría y el autor antes de actualizar
        Category category = categoryRepository.findById(updateArticle.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + updateArticle.getCategoryId()));

        // CAMBIAR
        User user = userRepository.findById(updateArticle.getCreatorId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + updateArticle.getCreatorId()));
        Optional<Creator> creator = creatorRepository.findByUser(user);
        if(creator.isEmpty()) {
            throw new ResourceNotFoundException("Creador no encontrado para el usuario con id: " + user.getId());
        }

        //Actualización de los campos del Articulo
        //article = articleMapper.toEntity(updateArticle);

        articleFromDb.setTitle(updateArticle.getTitle());
        articleFromDb.setContent(updateArticle.getContent());
        articleFromDb.setSlug(updateArticle.getSlug());
        articleFromDb.setCoverPath(updateArticle.getCoverPath());
        articleFromDb.setFilePath(updateArticle.getFilePath());

        articleFromDb.setCategory(category);
        articleFromDb.setCreator(creator.get());
        articleFromDb.setUpdatedAt(LocalDateTime.now());

        return articleMapper.toDetailsDTO(articleRepository.save(articleFromDb));
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Articulo no encontrado con el id: " + id));
        articleRepository.delete(article);
    }

    @Override
    public List<ArticleDetailsDTO> findTop6ArticlesByCreatedAt() {
        return articleRepository.findTop6ByOrderByCreatedAtDesc()
                .stream()
                .map(articleMapper::toDetailsDTO)
                .toList();
    }
}