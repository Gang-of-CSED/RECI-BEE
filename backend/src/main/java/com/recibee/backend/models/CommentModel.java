package com.recibee.backend.models;

import java.io.Serializable;

public class CommentModel implements Serializable {
    private String recipeId;
    private String comment;
    private double rating;
    private String user;

    public CommentModel(String recipeId, String comment, double rating, String user) {
        this.recipeId = recipeId;
        this.comment = comment;
        this.rating = rating;
        this.user = user;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }




}