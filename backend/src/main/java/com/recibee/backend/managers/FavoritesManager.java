package com.recibee.backend.managers;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.recibee.backend.models.FavoritesModel;

public class FavoritesManager {
    // private static final String FAVORITES_FILE_PATH = "backend\\src\\main\\java\\com\\recibee\\backend\\data\\favorites.json";
    private static final String FAVORITES_FILE_PATH = Paths.get("").toAbsolutePath().toString()
            + "\\src\\main\\java\\com\\recibee\\backend\\data\\favorites.json";
    private static FavoritesManager instance = null;
    private Map<String, FavoritesModel> favoritesMap = new HashMap<String, FavoritesModel>();

    private FavoritesManager() {
    }

    public static FavoritesManager getInstance() {
        if (instance == null) {
            instance = new FavoritesManager();
            FavoritesManager.getInstance().loadFavorites();
        }
        return instance;
    }

    public FavoritesModel getFavorites(String userId) {
        return FavoritesManager.getInstance().favoritesMap.get(userId);
    }

    public void addFavorites(String userId, ArrayList<Integer> favorites) {
        FavoritesManager.getInstance().favoritesMap.put(userId, new FavoritesModel(userId, favorites));
    }

    public void removeFavorites(String userId) {
        FavoritesManager.getInstance().favoritesMap.remove(userId);
    }

    public void addFavorite(String userId, int recipeId) {
        if (!FavoritesManager.getInstance().favoritesMap.containsKey(userId)) {
            FavoritesManager.getInstance().favoritesMap.put(userId,
                    new FavoritesModel(userId, new ArrayList<Integer>()));
        }
        FavoritesManager.getInstance().favoritesMap.get(userId).addFavorite(recipeId);
        System.out.println("Added favorite: " + recipeId + " to user: " + userId);
    }

    public void removeFavorite(String userId, int recipeId) {
        if (!FavoritesManager.getInstance().favoritesMap.containsKey(userId)) {
            return;
        }
        FavoritesManager.getInstance().favoritesMap.get(userId).removeFavorite(recipeId);
        System.out.println("Removed favorite: " + recipeId + " from user: " + userId);
    }

    public boolean isFavorite(String userId, int recipeId) {
        return FavoritesManager.getInstance().favoritesMap.get(userId).isFavorite(recipeId);
    }

    public ArrayList<Integer> getFavoritesList(String userId) {
        return FavoritesManager.getInstance().favoritesMap.get(userId).getFavorites();
    }

    public void updateFavorites(String userId, ArrayList<Integer> favorites) {
        FavoritesManager.getInstance().favoritesMap.get(userId).setFavorites(favorites);
    }

    public void printFavorites() {
        for (FavoritesModel favorites : FavoritesManager.getInstance().favoritesMap.values()) {
            System.out.println(favorites.toMap());
        }
    }

    public void saveFavorites() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(FavoritesManager.getInstance().favoritesMap);
            Path path = Paths.get(FAVORITES_FILE_PATH);
            Files.writeString(path, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadFavorites() {
        try {
            Path path = Paths.get(FAVORITES_FILE_PATH);
            String json = Files.readString(path);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            for (JsonNode node : root) {
                JsonNode userIdNode = node.get("userId");
                String userId = userIdNode.asText();
                JsonNode favoritesNode = node.get("favorites");
                ArrayList<Integer> favorites = new ArrayList<Integer>();
                for (JsonNode favorite : favoritesNode) {
                    favorites.add(favorite.asInt());
                }
                FavoritesManager.getInstance().favoritesMap.put(userId, new FavoritesModel(userId, favorites));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FavoritesManager favoritesManager = FavoritesManager.getInstance();
        // favoritesManager.addFavorites(1, new ArrayList<>(Arrays.asList(1, 4, 8,
        // 19)));
        // favoritesManager.addFavorites(2, new ArrayList<>(Arrays.asList(2, 5, 9,
        // 20)));
        // favoritesManager.addFavorites(3, new ArrayList<>(Arrays.asList(0, 2, 15, 17,
        // 18, 20)));
        // favoritesManager.addFavorites(4, new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9,
        // 11, 13, 15, 17, 19)));
        // favoritesManager.addFavorites(5, new ArrayList<>(Arrays.asList(0, 2, 4, 6, 8,
        // 10, 12, 14, 16, 18)));
        // favoritesManager.printFavorites();
        // favoritesManager.saveFavorites();
        favoritesManager.printFavorites();

    }
}
