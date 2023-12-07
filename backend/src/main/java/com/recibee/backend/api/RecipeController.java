package com.recibee.backend.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import com.recibee.backend.managers.RecipeManager;
import com.recibee.backend.models.RecipeModel;

@RestController
@CrossOrigin(origins = { "http://localhost:8081" })
public class RecipeController {
    @PostMapping("/recipe/{id}")
    public RecipeModel getRecipe(@PathVariable("id") String id) {
        if (id == null) {
            return null;
        }
        if (Integer.parseInt(id) >= RecipeManager.getInstance().getRecipes().size()) {
            return null;
        }
        System.out.println(RecipeManager.getInstance().getRecipe(Integer.parseInt(id)).toMap());
        return RecipeManager.getInstance().getRecipe(Integer.parseInt(id));
    }

    @PostMapping("/recipes")
    public RecipeModel[] getRecipes() {
        return RecipeManager.getInstance().getRecipes().values().toArray(new RecipeModel[0]);
    }
}
