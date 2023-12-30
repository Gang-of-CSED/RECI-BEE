package com.recibee.backend.models;

import java.util.ArrayList;
import java.util.Map;

public class SavesModel {
    String userId;
    ArrayList<Integer> saves;

    public SavesModel() {

    }

    public SavesModel(String userId, ArrayList<Integer> saves) {
        this.userId = userId;
        this.saves = saves;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String id) {
        this.userId = id;
    }

    public ArrayList<Integer> getSaves() {
        return saves;
    }

    public void setSaves(ArrayList<Integer> saves) {
        this.saves = saves;
    }

    public void addSave(int id) {
        if (this.isSave(id)) {
            return;
        }
        this.saves.add(id);
    }

    public void removeSave(int id) {
        if (!this.saves.contains(id)) {
            return;
        }
        this.saves.remove(saves.indexOf(id));
        System.out.println("Removed save: " + id + " from user: " + this.userId);
    }

    public boolean isSave(int id) {
        return this.saves.contains(id);
    }

    public Map<String, Object> toMap() {
        return Map.of("userId", this.userId, "saves", this.saves);
    }
}
