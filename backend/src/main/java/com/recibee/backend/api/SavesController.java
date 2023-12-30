package com.recibee.backend.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.recibee.backend.managers.SavesManager;
import com.recibee.backend.models.SavesModel;

@RestController
@CrossOrigin(origins = { "http://localhost:8081" })
public class SavesController {
    @GetMapping("/{userId}/save/{recipeId}")
    public boolean isSave(@PathVariable("userId") String userId, @PathVariable("recipeId") String recipeId) {
        if (userId == null || recipeId == null) {
            return false;
        }

        if (SavesManager.getInstance().isSave((userId), Integer.parseInt(recipeId)) == true) {
            return true;
        }
        return false;
    }

    @GetMapping("/{userId}/saves")
    public SavesModel getSaves(@PathVariable("userId") String userId) {
        if (userId == null) {
            return null;
        }
        return SavesManager.getInstance().getSaves((userId));
    }

    @PutMapping("/{userId}/save/{recipeId}")
    public void addSave(@PathVariable("userId") String userId, @PathVariable("recipeId") String recipeId) {
        System.out.println("addSave");
        if (userId == null || recipeId == null) {
            return;
        }
        SavesManager.getInstance().addSave((userId), Integer.parseInt(recipeId));
    }

    @PutMapping("/{userId}/unsave/{recipeId}")
    public void removeSave(@PathVariable("userId") String userId, @PathVariable("recipeId") String recipeId) {
        if (userId == null || recipeId == null) {
            return;
        }
        SavesManager.getInstance().removeSave((userId), Integer.parseInt(recipeId));
    }

}
