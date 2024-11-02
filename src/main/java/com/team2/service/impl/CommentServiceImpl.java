package com.team2.service.impl;

import com.team2.dto.comment.CommentCreateDTO;
import com.team2.dto.comment.CommentDetailsDTO;
import com.team2.exception.ResourceNotFoundException;
import com.team2.mapper.CommentMapper;
import com.team2.model.entity.Article;
import com.team2.model.entity.Comment;
import com.team2.repository.ArticleRepository;
import com.team2.repository.CommentRepository;
import com.team2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final CommentMapper commentMapper;

    @Transactional(readOnly = true)
    @Override
    public List<CommentDetailsDTO> getAll() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(commentMapper::toDetailsDTO)
                .toList();
    }

    @Transactional
    @Override
    public CommentDetailsDTO create(CommentCreateDTO commentCreateDTO) {
        Article article = articleRepository.findById(commentCreateDTO.getArticleId())
                .orElseThrow(() -> new ResourceNotFoundException("La publicacion no existe con id:" + commentCreateDTO.getArticleId()));
        Comment comment = commentMapper.toEntity(commentCreateDTO);
        comment.setArticle(article);
        comment.setCreatedAt(LocalDateTime.now());
        return commentMapper.toDetailsDTO(commentRepository.save(comment));
    }

    @Transactional
    @Override
    public void delete(Integer id){
        Comment comment = commentRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("El comentario no existe con id:" + id));
        commentRepository.delete(comment);
    }
}