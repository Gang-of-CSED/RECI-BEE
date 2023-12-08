package com.recibee.backend.api;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recibee.backend.managers.CommentManager;
import com.recibee.backend.models.CommentModel;

@RestController
@CrossOrigin(origins = { "http://localhost:8081" })
@RequestMapping("/comments")
public class CommentController {
    private CommentManager commentManager = CommentManager.getInstance();

    @PostMapping
    public CommentModel createComment(@RequestBody CommentModel comment) {
        System.out.println("comment");
        return commentManager.createComment(comment);
    }

    @GetMapping("/{recipeId}")
    public List<CommentModel> getCommentsForRecipe(@PathVariable String recipeId) {
        return commentManager.getCommentsForRecipe(recipeId);
    }
}