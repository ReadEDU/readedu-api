package com.team2.api;

import com.team2.dto.comment.CommentCreateDTO;
import com.team2.dto.comment.CommentDetailsDTO;
import com.team2.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comments")

public class CommentController {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<List<CommentDetailsDTO>> list() {
        List<CommentDetailsDTO> comments = commentService.getAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<CommentDetailsDTO> create(@Valid @RequestBody CommentCreateDTO commentCreateDTO) {
        CommentDetailsDTO createcomment = commentService.create(commentCreateDTO);
        return new ResponseEntity<>(createcomment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        commentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

