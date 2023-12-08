package com.recibee.backend.managers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.recibee.backend.models.CommentModel;
import com.recibee.backend.utils.FileManager;

public class CommentManager {
    private static CommentManager instance = null;
    private Map<String, CommentModel> Comments = new HashMap<String, CommentModel>();

    private CommentManager() {
    }

    public static synchronized CommentManager getInstance() {
        if (instance == null) {
            instance = new CommentManager();
            instance.Comments = FileManager.readObjectFromFile("comments.dat");

            if(instance.Comments==null){
                System.out.println("comments.dat not found, creating new file");
                instance.Comments = new HashMap<String, CommentModel>();
                FileManager.saveObjectToFile(instance.Comments, "comments.dat");
            }
        }
        return instance;
    }

    public CommentModel createComment(CommentModel comment) {
        Comments.put(comment.getRecipeId(), comment);
        FileManager.saveObjectToFile(Comments, "comments.dat");
        return comment;
    }

    public List<CommentModel> getCommentsForRecipe(String recipeId) {
        List<CommentModel> comments = new ArrayList<CommentModel>();
        for (CommentModel comment : Comments.values()) {
            if (comment.getRecipeId().equals(recipeId)) {
                comments.add(comment);
            }
        }
        return comments;
    }

}