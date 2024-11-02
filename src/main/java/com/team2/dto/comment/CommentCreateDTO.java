package com.team2.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentCreateDTO {
    private Integer id;

    @NotBlank(message = "El contenido es obligatorio")
    @Size(message = "El contenido debe tener 250 caracteres o menos")
    private String content;

    @NotNull(message = "La publicación es obligatoria")
    private Integer articleId;
}
