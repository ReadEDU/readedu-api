package com.team2.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "collection_articles")
@IdClass(CollectionArticlePK.class)
public class CollectionArticle {

    @Id
    private Integer favorite;
    @Id
    private Integer article;

    @Column(name = "added_date", nullable = false)
    private LocalDateTime addedDate;
}
