package com.team2.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "detail_articles")
public class DetailArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "file")
    private String file;

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_detail_article"))
    @JsonBackReference
    private Article article;
}
