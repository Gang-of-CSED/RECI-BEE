package com.recibee.backend.models;

import java.util.Map;

import com.recibee.backend.managers.RecipeManager;
import java.util.HashMap;
import java.util.Map;
import java.util.HashMap;
import java.util.Map;

public class RecipeModel {
    int id;
    String name;
    String description;
    String ingredients;
    String[] categories;
    int time;
    String steps;
    Double rate;
    String cover;
    String cuisine;
    int calories;
    String[] diet;

    public RecipeModel() {

    }

    public RecipeModel(int id, String name, String decription, String ingredients, String[] categories, int time,
            String steps,
            Double rate,
            String imagePath,
            String cuisine,
            int calories,
            String[] diet) {
        this.id = id;
        this.name = name;
        this.description = decription;
        this.ingredients = ingredients;
        this.categories = categories;
        this.time = time;
        this.steps = steps;
        this.rate = rate;
        this.cover = imagePath;
        this.cuisine = cuisine;
        this.calories = calories;
        this.diet = diet;
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

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
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

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine= cuisine;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories= calories;
    }

    public String[] getDiet() {
        return diet;
    }

    public void setDiet(String[] diet) {
        this.diet= diet;
    }
    

    

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("description", description);
        map.put("ingredients", ingredients);
        map.put("categories", categories);
        map.put("time", time);
        map.put("steps", steps);
        map.put("rate", rate);
        map.put("cover", cover);
        map.put("cuisine", cuisine);
        map.put("calories", calories);
        map.put("diet", diet);
        return map;
    }
}
