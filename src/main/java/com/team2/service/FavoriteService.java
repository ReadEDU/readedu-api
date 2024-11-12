package com.team2.service;

import com.team2.dto.favorite.FavoriteCreateUpdateDTO;
import com.team2.dto.favorite.FavoriteDetailsDTO;

import java.util.List;

public interface FavoriteService {
    FavoriteDetailsDTO createFavorite(FavoriteCreateUpdateDTO favoriteDTO);
    List<FavoriteDetailsDTO> getFavoritesByUser(Integer userId);
    FavoriteDetailsDTO getFavoriteById(Integer favoriteId);
    FavoriteDetailsDTO updateFavorite(Integer favoriteId, FavoriteCreateUpdateDTO favoriteDTO);
    void deleteFavorite(Integer favoriteId);
}