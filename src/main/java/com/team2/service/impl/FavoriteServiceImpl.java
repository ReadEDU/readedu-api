package com.team2.service.impl;

import com.team2.dto.favorite.FavoriteCreateUpdateDTO;
import com.team2.dto.favorite.FavoriteDetailsDTO;
import com.team2.exception.ResourceNotFoundException;
import com.team2.mapper.FavoriteMapper;
import com.team2.model.entity.Favorite;
import com.team2.model.entity.Reader;
import com.team2.repository.FavoriteRepository;
import com.team2.repository.ReaderRepository;
import com.team2.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final ReaderRepository readerRepository;
    private final FavoriteMapper favoriteMapper;

    @Override
    @Transactional
    public FavoriteDetailsDTO createFavorite(FavoriteCreateUpdateDTO favoriteDTO) {
        // Verificar si el lector existe
        Reader reader = readerRepository.findById(favoriteDTO.getReaderId())
                .orElseThrow(() -> new ResourceNotFoundException("Reader not found with ID: " + favoriteDTO.getReaderId()));

        // Convertir el DTO en una entidad Favorite
        Favorite favorite = favoriteMapper.toEntity(favoriteDTO);
        favorite.setReader(reader);  // Asignar al lector
        favorite.setCreatedAt(LocalDateTime.now());

        // Guardar la colección y mapear el resultado a DTO
        Favorite savedFavorite = favoriteRepository.save(favorite);
        return favoriteMapper.toDetailsDto(savedFavorite);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FavoriteDetailsDTO> getFavoritesByUser(Integer userId) {
        List<Favorite> favorites = favoriteRepository.findByReaderId(userId);
        return favorites.stream()
                .map(favoriteMapper::toDetailsDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public FavoriteDetailsDTO getFavoriteById(Integer favoriteId) {
        Favorite favorite = favoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite not found with ID: " + favoriteId));
        return favoriteMapper.toDetailsDto(favorite);
    }

    @Override
    @Transactional
    public FavoriteDetailsDTO updateFavorite(Integer favoriteId, FavoriteCreateUpdateDTO favoriteDTO) {
        // Buscar el favorito existente
        Favorite existingFavorite = favoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite not found with ID: " + favoriteId));

        // Actualizar los campos del favorito
        existingFavorite.setName(favoriteDTO.getName());
        existingFavorite.setUpdatedAt(LocalDateTime.now());

        // Guardar y retornar el DTO actualizado
        Favorite updatedFavorite = favoriteRepository.save(existingFavorite);
        return favoriteMapper.toDetailsDto(updatedFavorite);
    }

    @Transactional
    @Override
    public void deleteFavorite(Integer favoriteId) {
        Favorite favorite = favoriteRepository.findById(favoriteId)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite not found with ID: " + favoriteId));

        favoriteRepository.delete(favorite);
    }
}