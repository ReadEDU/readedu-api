package com.team2.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "reader_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_favorite_reader"))
    private Reader reader;

    @OneToMany(mappedBy = "favorite", cascade = CascadeType.ALL)
    private List<CollectionArticle> collectionArticles;
}
