package com.team2.mapper;

import com.team2.dto.article.ArticleCreateUpdateDTO;
import com.team2.dto.article.ArticleDetailsDTO;
import com.team2.model.entity.Article;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {
    private final ModelMapper modelMapper;

    public ArticleMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        //Configurar ModelMapper para usar estrategia de coincidencia estricta
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public ArticleDetailsDTO toDetailsDTO(Article article) {
           ArticleDetailsDTO articleDetailsDTO = modelMapper.map(article, ArticleDetailsDTO.class);

           articleDetailsDTO.setCreatorName(article.getCreator().getFirstName()+" "+article.getCreator().getLastName());
           articleDetailsDTO.setCategoryName(article.getCategory().getName());

           return articleDetailsDTO;
    }

    public Article toEntity(ArticleCreateUpdateDTO articleCreateUpdateDTO) {
        return modelMapper.map(articleCreateUpdateDTO, Article.class);
    }

    public ArticleCreateUpdateDTO toCreateUpdateDTO(Article article) {
        return modelMapper.map(article, ArticleCreateUpdateDTO.class);
    }

}
