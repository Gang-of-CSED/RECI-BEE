package com.recibee.backend.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.recibee.backend.managers.FavoritesManager;
import com.recibee.backend.models.FavoritesModel;

@RestController
@CrossOrigin(origins = { "http://localhost:8081" })
public class FavoritesController {
    @GetMapping("/{userId}/favorite/{recipeId}")
    public boolean isFavorite(@PathVariable("userId") String userId, @PathVariable("recipeId") String recipeId) {
        if (userId == null || recipeId == null) {
            return false;
        }

        if (FavoritesManager.getInstance().isFavorite(Integer.parseInt(userId), Integer.parseInt(recipeId)) == true) {
            return true;
        }
        return false;
    }

    @GetMapping("/{userId}/favorites")
    public FavoritesModel getFavorites(@PathVariable("userId") String userId) {
        if (userId == null) {
            return null;
        }
        return FavoritesManager.getInstance().getFavorites(Integer.parseInt(userId));
    }

    @PutMapping("/{userId}/favorite/{recipeId}")
    public void addFavorite(@PathVariable("userId") String userId, @PathVariable("recipeId") String recipeId) {
        System.out.println("addFavorite");
        if (userId == null || recipeId == null) {
            return;
        }
        FavoritesManager.getInstance().addFavorite(Integer.parseInt(userId), Integer.parseInt(recipeId));
    }

    @PutMapping("/{userId}/unfavorite/{recipeId}")
    public void removeFavorite(@PathVariable("userId") String userId, @PathVariable("recipeId") String recipeId) {
        if (userId == null || recipeId == null) {
            return;
        }
        FavoritesManager.getInstance().removeFavorite(Integer.parseInt(userId), Integer.parseInt(recipeId));
    }

}
