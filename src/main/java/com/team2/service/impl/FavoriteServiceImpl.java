package com.team2.service.impl;

import com.team2.model.entity.Favorite;
import com.team2.repository.FavoriteRepository;
import com.team2.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;

    @Transactional
    @Override
    public Favorite createFavorite(Favorite favorite) {
        favorite.setCreatedAt(LocalDateTime.now());
        return favoriteRepository.save(favorite);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Favorite> getFavoritesByUser(Integer userId) {
        return favoriteRepository.findByReaderId(userId);
    }

    @Transactional(readOnly = true)
    @Override
    public Favorite getFavoriteById(Integer favoriteId) {
        return favoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new RuntimeException("Collection not found"));
    }

    @Transactional
    @Override
    public Favorite updateFavorite(Integer favoriteId, Favorite updatedFavorite) {
        Favorite existingFavorite = getFavoriteById(favoriteId);
        existingFavorite.setName(updatedFavorite.getName());
        existingFavorite.setUpdatedAt(LocalDateTime.now());
        return favoriteRepository.save(existingFavorite);
    }

    @Transactional
    @Override
    public void deleteFavorite(Integer favoriteId) {
        Favorite favorite = getFavoriteById(favoriteId);
        favoriteRepository.delete(favorite);
    }
}
