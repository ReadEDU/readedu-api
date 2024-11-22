package com.team2.service.impl;

import com.team2.dto.article.ArticleDetailsDTO;
import com.team2.mapper.ArticleMapper;
import com.team2.repository.ArticleRepository;
import com.team2.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    @Override
    public List<ArticleDetailsDTO> findTop6ArticlesByCreatedAt() {
        return articleRepository.findTop6ByOrderByCreatedAtDesc().stream()
                .map(articleMapper::toDetailsDTO)
                .collect(Collectors.toList());
    }
}
