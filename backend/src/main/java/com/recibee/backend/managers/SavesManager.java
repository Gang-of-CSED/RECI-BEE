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
import com.recibee.backend.models.SavesModel;

public class SavesManager {
    // private static final String FAVORITES_FILE_PATH = "backend\\src\\main\\java\\com\\recibee\\backend\\data\\saves.json";
    private static final String FAVORITES_FILE_PATH = Paths.get("").toAbsolutePath().toString()
            + "\\src\\main\\java\\com\\recibee\\backend\\data\\saves.json";
    private static SavesManager instance = null;
    private Map<String, SavesModel> savesMap = new HashMap<String, SavesModel>();

    private SavesManager() {
    }

    public static SavesManager getInstance() {
        if (instance == null) {
            instance = new SavesManager();
            SavesManager.getInstance().loadSaves();
        }
        return instance;
    }

    public SavesModel getSaves(String userId) {
        return SavesManager.getInstance().savesMap.get(userId);
    }

    public void addSaves(String userId, ArrayList<Integer> saves) {
        SavesManager.getInstance().savesMap.put(userId, new SavesModel(userId, saves));
    }

    public void removeSaves(String userId) {
        SavesManager.getInstance().savesMap.remove(userId);
    }

    public void addSave(String userId, int recipeId) {
        if (!SavesManager.getInstance().savesMap.containsKey(userId)) {
            SavesManager.getInstance().savesMap.put(userId,
                    new SavesModel(userId, new ArrayList<Integer>()));
        }
        SavesManager.getInstance().savesMap.get(userId).addSave(recipeId);
        System.out.println("Added save: " + recipeId + " to user: " + userId);
    }

    public void removeSave(String userId, int recipeId) {
        if (!SavesManager.getInstance().savesMap.containsKey(userId)) {
            return;
        }
        SavesManager.getInstance().savesMap.get(userId).removeSave(recipeId);
        System.out.println("Removed save: " + recipeId + " from user: " + userId);
    }

    public boolean isSave(String userId, int recipeId) {
        return SavesManager.getInstance().savesMap.get(userId).isSave(recipeId);
    }

    public ArrayList<Integer> getSavesList(String userId) {
        return SavesManager.getInstance().savesMap.get(userId).getSaves();
    }

    public void updateSaves(String userId, ArrayList<Integer> saves) {
        SavesManager.getInstance().savesMap.get(userId).setSaves(saves);
    }

    public void printSaves() {
        for (SavesModel saves : SavesManager.getInstance().savesMap.values()) {
            System.out.println(saves.toMap());
        }
    }

    public void saveSaves() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(SavesManager.getInstance().savesMap);
            Path path = Paths.get(FAVORITES_FILE_PATH);
            Files.writeString(path, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSaves() {
        try {
            Path path = Paths.get(FAVORITES_FILE_PATH);
            String json = Files.readString(path);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            for (JsonNode node : root) {
                JsonNode userIdNode = node.get("userId");
                String userId = userIdNode.asText();
                JsonNode savesNode = node.get("saves");
                ArrayList<Integer> saves = new ArrayList<Integer>();
                for (JsonNode save : savesNode) {
                    saves.add(save.asInt());
                }
                SavesManager.getInstance().savesMap.put(userId, new SavesModel(userId, saves));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SavesManager savesManager = SavesManager.getInstance();
        // savesManager.addSaves(1, new ArrayList<>(Arrays.asList(1, 4, 8,
        // 19)));
        // savesManager.addSaves(2, new ArrayList<>(Arrays.asList(2, 5, 9,
        // 20)));
        // savesManager.addSaves(3, new ArrayList<>(Arrays.asList(0, 2, 15, 17,
        // 18, 20)));
        // savesManager.addSaves(4, new ArrayList<>(Arrays.asList(1, 3, 5, 7, 9,
        // 11, 13, 15, 17, 19)));
        // savesManager.addSaves(5, new ArrayList<>(Arrays.asList(0, 2, 4, 6, 8,
        // 10, 12, 14, 16, 18)));
        // savesManager.printSaves();
        // savesManager.saveSaves();
        savesManager.printSaves();

    }
}
