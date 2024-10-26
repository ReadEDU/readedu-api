package com.team2.service;

import com.team2.model.entity.Favorite;

import java.util.List;

public interface FavoriteService {
    Favorite createFavorite(Favorite favorite);
    List<Favorite> getFavoritesByUser(Integer userId);
    Favorite getFavoriteById(Integer favoriteId);
    Favorite updateFavorite(Integer favoriteId, Favorite favorite);
    void deleteFavorite(Integer favoriteId);
}
