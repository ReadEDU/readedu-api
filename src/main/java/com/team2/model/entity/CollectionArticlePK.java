package com.team2.model.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class CollectionArticlePK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id"
    , foreignKey = @ForeignKey(name = "fk_collectionarticles_articles"))
    private Article article;

    @ManyToOne
    @JoinColumn(name = "favorite_id", referencedColumnName = "id"
    , foreignKey = @ForeignKey(name = "fk_collectionarticles_favorites"))
    private Favorite favorite;
}
