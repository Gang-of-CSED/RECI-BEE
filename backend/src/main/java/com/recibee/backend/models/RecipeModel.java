package com.recibee.backend.models;

import java.util.Map;

import com.recibee.backend.managers.RecipeManager;

public class RecipeModel {
    int id;
    String name;
    String description;
    String ingredients;
    int time;
    String steps;
    Double rate;
    String cover;

    public RecipeModel() {

    }

    public RecipeModel(int id, String name, String decription, String ingredients, int time, String steps,
            Double rate,
            String imagePath) {
        this.id = id;
        this.name = name;
        this.description = decription;
        this.ingredients = ingredients;
        this.time = time;
        this.steps = steps;
        this.rate = rate;
        this.cover = imagePath;
        // this.cover = RecipeManager.getInstance().encodedImageToBase64(imagePath);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Map<String, Object> toMap() {
        return Map.of(
                "id", id,
                "name", name,
                "description", description,
                "ingredients", ingredients,
                "time", time,
                "steps", steps,
                "rate", rate,
                "cover", cover);
    }
}
