package com.team2.api;

import com.team2.dto.favorite.FavoriteCreateUpdateDTO;
import com.team2.dto.favorite.FavoriteDetailsDTO;
import com.team2.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('READER', 'ADMIN')")
public class FavoriteController {

    private final FavoriteService favoriteService;

    // Crear un nuevo favorito
    @PostMapping
    public ResponseEntity<FavoriteDetailsDTO> createFavorite(@RequestBody FavoriteCreateUpdateDTO favoriteDTO) {
        FavoriteDetailsDTO savedFavorite = favoriteService.createFavorite(favoriteDTO);
        return new ResponseEntity<>(savedFavorite, HttpStatus.CREATED);
    }

    // Obtener favoritos por usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavoriteDetailsDTO>> getFavoritesByUser(@PathVariable Integer userId) {
        List<FavoriteDetailsDTO> favorites = favoriteService.getFavoritesByUser(userId);
        return ResponseEntity.ok(favorites);
    }

    // Obtener un favorito por ID
    @GetMapping("/{favoriteId}")
    public ResponseEntity<FavoriteDetailsDTO> getFavoriteById(@PathVariable Integer favoriteId) {
        FavoriteDetailsDTO favorite = favoriteService.getFavoriteById(favoriteId);
        return ResponseEntity.ok(favorite);
    }

    // Actualizar un favorito existente
    @PutMapping("/{favoriteId}")
    public ResponseEntity<FavoriteDetailsDTO> updateFavorite(@PathVariable Integer favoriteId, @RequestBody FavoriteCreateUpdateDTO favoriteDTO) {
        FavoriteDetailsDTO updatedFavorite = favoriteService.updateFavorite(favoriteId, favoriteDTO);
        return ResponseEntity.ok(updatedFavorite);
    }

    // Eliminar un favorito
    @DeleteMapping("/{favoriteId}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Integer favoriteId) {
        favoriteService.deleteFavorite(favoriteId);
        return ResponseEntity.noContent().build();
    }
}