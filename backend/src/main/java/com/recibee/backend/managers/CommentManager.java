package com.recibee.backend.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.recibee.backend.models.CommentModel;
import com.recibee.backend.utils.FileManager;

public class CommentManager {
    private static CommentManager instance = null;
    private Map<String, List<CommentModel>> Comments = null;

    private CommentManager() {
    }

    public static synchronized CommentManager getInstance() {
        if (instance == null) {
            instance = new CommentManager();
            instance.Comments = FileManager.readObjectFromFile("comments.dat");

            if(instance.Comments==null){
                System.out.println("comments.dat not found, creating new file");
                instance.Comments = new HashMap<String, List<CommentModel>>();
                FileManager.saveObjectToFile(instance.Comments, "comments.dat");
            }
        }
        return instance;
    }

    public CommentModel createComment(CommentModel comment) {
        if(Comments.get(comment.getRecipeId()) == null) Comments.put(comment.getRecipeId(), new ArrayList<CommentModel>());    
        Comments.get(comment.getRecipeId()).add(comment);
        FileManager.saveObjectToFile(Comments, "comments.dat");
        return comment;
    }

    public List<CommentModel> getCommentsForRecipe(String recipeId) {
        return Comments.get(recipeId);
    }

}