package com.team2.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;
    private LocalDateTime date;
    private String author;

    @ManyToOne
    @JoinColumn(name = "reader_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_comment_reader"))
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_comment_article"))
    private Article article;
}

