package com.recibee.backend.models;

import java.util.ArrayList;
import java.util.Map;

public class FavoritesModel {
    int userId;
    ArrayList<Integer> favorites;

    public FavoritesModel() {

    }

    public FavoritesModel(int userId, ArrayList<Integer> favorites) {
        this.userId = userId;
        this.favorites = favorites;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public ArrayList<Integer> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Integer> favorites) {
        this.favorites = favorites;
    }

    public void addFavorite(int id) {
        if (this.isFavorite(id)) {
            return;
        }
        this.favorites.add(id);
    }

    public void removeFavorite(int id) {
        if (!this.favorites.contains(id)) {
            return;
        }
        this.favorites.remove(favorites.indexOf(id));
        System.out.println("Removed favorite: " + id + " from user: " + this.userId);
    }

    public boolean isFavorite(int id) {
        return this.favorites.contains(id);
    }

    public Map<String, Object> toMap() {
        return Map.of("userId", this.userId, "favorites", this.favorites);
    }
}
