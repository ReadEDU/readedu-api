package com.team2.api;

import com.team2.model.entity.Favorite;
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

    @PostMapping
    public ResponseEntity<Favorite> createFavorite(@RequestBody Favorite favorite) {
        Favorite savedFavorite = favoriteService.createFavorite(favorite);
        return new ResponseEntity<>(savedFavorite, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Favorite>> getFavoritesByUserId(@PathVariable Integer userId) {
        List<Favorite> favorites = favoriteService.getFavoritesByUser(userId);
        return ResponseEntity.ok(favorites);
    }

    @GetMapping("/{favoriteId}")
    public ResponseEntity<Favorite> getFavoriteById(@PathVariable Integer favoriteId) {
        Favorite favorite = favoriteService.getFavoriteById(favoriteId);
        return ResponseEntity.ok(favorite);
    }

    @PutMapping("/{favoriteId}")
    public ResponseEntity<Favorite> updateFavorite(@PathVariable Integer favoriteId,
                                                       @RequestBody Favorite favorite) {
        Favorite updateFavorite = favoriteService.updateFavorite(favoriteId, favorite);
        return ResponseEntity.ok(updateFavorite);
    }

    @DeleteMapping("/{favoriteId}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Integer favoriteId) {
        favoriteService.deleteFavorite(favoriteId);
        return ResponseEntity.noContent().build();
    }
}
