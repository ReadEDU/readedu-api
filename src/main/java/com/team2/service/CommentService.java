package com.team2.service;

import com.team2.dto.comment.CommentCreateDTO;
import com.team2.dto.comment.CommentDetailsDTO;
import com.team2.model.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentService {
    List<CommentDetailsDTO> getAll();
    CommentDetailsDTO create(CommentCreateDTO commentCreateDTO);
    void delete(Integer id);
}
