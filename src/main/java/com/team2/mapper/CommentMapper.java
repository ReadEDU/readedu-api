package com.team2.mapper;

import com.team2.dto.comment.CommentCreateDTO;
import com.team2.dto.comment.CommentDetailsDTO;
import com.team2.model.entity.Comment;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    private final ModelMapper modelMapper;

    public CommentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public CommentDetailsDTO toDetailsDTO(Comment comment) {
        CommentDetailsDTO commentDetailsDTO = modelMapper.map(comment, CommentDetailsDTO.class);
        commentDetailsDTO.setArticleTitle(comment.getArticle().getTitle());
        return commentDetailsDTO;
    }

    public Comment toEntity(CommentCreateDTO commentCreateDTO) {
        return modelMapper.map(commentCreateDTO, Comment.class);
    }

    public CommentCreateDTO toEntity(Comment comment) {
        return modelMapper.map(comment, CommentCreateDTO.class);
    }
}
