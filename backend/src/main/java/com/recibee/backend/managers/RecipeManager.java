package com.recibee.backend.managers;

import com.recibee.backend.models.RecipeModel;
import com.fasterxml.jackson.databind.JsonSerializable.Base;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;

public class RecipeManager {
    private static RecipeManager instance = null;
    private Map<Integer, RecipeModel> recipes = new HashMap<Integer, RecipeModel>();

    private RecipeManager() {
    }

    public static synchronized RecipeManager getInstance() {
        if (instance == null) {
            instance = new RecipeManager();
            RecipeManager.getInstance().loadRecipes();
        }
        return instance;
    }

    public RecipeModel getRecipe(int id) {
        return recipes.get(id);
    }

    public void addRecipe(RecipeModel recipe) {
        recipes.put(recipe.getId(), recipe);
    }

    public void removeRecipe(int id) {
        recipes.remove(id);
    }

    public void updateRecipe(RecipeModel recipe) {
        recipes.put(recipe.getId(), recipe);
    }

    public Map<Integer, RecipeModel> getRecipes() {
        return recipes;
    }

    public String encodedImageToBase64(String imagePath) {
        try {
            byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
            return base64Image;
        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }

    public void addRecipe(String name, String description, String ingredients, int time, String steps, Double rate,
            String imagePath) {
        int id = recipes.size();
        RecipeModel recipe = new RecipeModel(id, name, description, ingredients, time, steps, rate, imagePath);
        recipes.put(id, recipe);
    }

    public void updateRecipe(int id, String name, String description, String ingredients, int time, String steps,
            Double rate,
            String imagePath) {
        RecipeModel recipe = new RecipeModel(id, name, description, ingredients, time, steps, rate, imagePath);
        recipes.put(id, recipe);
    }

    public void printRecipes() {
        for (RecipeModel recipe : recipes.values()) {
            System.out.println(recipe.toMap());
        }
    }

    public void saveRecipes() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(recipes.values());
            Path path = Paths.get(
                    "D:\\Term 5\\HCI\\RECI-BEE\\backend\\src\\main\\java\\com\\recibee\\backend\\data\\recipes.json");
            Files.writeString(path, json);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loadRecipes() {
        try {
            Path path = Paths.get(
                    "D:\\Term 5\\HCI\\RECI-BEE\\backend\\src\\main\\java\\com\\recibee\\backend\\data\\recipes.json");
            String content = Files.readString(path);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(content);
            for (JsonNode recipeNode : jsonNode) {
                RecipeModel recipe = new RecipeModel();
                recipe.setId(recipeNode.path("id").asInt());
                recipe.setName(recipeNode.path("name").asText());
                recipe.setDescription(recipeNode.path("description").asText());
                recipe.setIngredients(recipeNode.path("ingredients").asText());
                recipe.setTime(recipeNode.path("time").asInt());
                recipe.setSteps(recipeNode.path("steps").asText());
                recipe.setRate(recipeNode.path("rate").asDouble());
                recipe.setCover(recipeNode.path("cover").asText());
                RecipeManager.getInstance().addRecipe(recipe);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        RecipeManager recipeManager = RecipeManager.getInstance();
        recipeManager.addRecipe("Bashamel Pasta",
                "A creamy pasta dish with homemade Béchamel sauce.",
                "['250g pasta', '2 cups milk', '4 tbsp butter', '4 tbsp all-purpose flour', 'Salt and pepper to taste']",
                120,
                "['1. Cook pasta according to package instructions.', '2. In a saucepan, melt butter over medium heat.', '3. Stir in flour and cook for 2-3 minutes until golden brown.', '4. Gradually whisk in milk, stirring constantly until the sauce thickens.', '5. Season with salt and pepper.', '6. Combine the cooked pasta with the Béchamel sauce.', '7. Serve hot and enjoy!']",
                4.0,
                "/static/BashamelPasta.jpg");
        recipeManager.addRecipe("Mac and Cheese",
                "A classic American dish of macaroni pasta baked with cheese.",
                "['250g pasta', '2 cups milk', '4 tbsp butter', '4 tbsp all-purpose flour', 'Salt and pepper to taste', '2 cups cheddar cheese']",
                120,
                "['1. Cook pasta according to package instructions.', '2. In a saucepan, melt butter over medium heat.', '3. Stir in flour and cook for 2-3 minutes until golden brown.', '4. Gradually whisk in milk, stirring constantly until the sauce thickens.', '5. Season with salt and pepper.', '6. Combine the cooked pasta with the Béchamel sauce.', '7. Top with cheddar cheese.', '8. Bake at 350°F for 20 minutes.', '9. Serve hot and enjoy!']",
                4.5,
                "/static/MacAndCheese.jpg");
        recipeManager.addRecipe("Spaghetti and Meatballs",
                "A classic Italian-American dish of spaghetti and meatballs in tomato sauce.",
                "['250g spaghetti', '1 lb ground beef', '1 onion', '2 cloves garlic', '1 egg', '1/2 cup breadcrumbs', '1 tsp salt', '1 tsp pepper', '1 tsp paprika', '1 tsp garlic powder', '1 tsp onion powder', '1 tsp oregano', '1 tsp basil', '1 can tomato sauce']",
                120,
                "['1. Cook pasta according to package instructions.', '2. In a bowl, combine ground beef, onion, garlic, egg, breadcrumbs, salt, pepper, paprika, garlic powder, onion powder, oregano, and basil.', '3. Form into meatballs.', '4. In a pan, brown meatballs over medium heat.', '5. Add tomato sauce and simmer for 20 minutes.', '6. Serve hot and enjoy!']",
                4.5,
                "/static/SpaghettiAndMeatballs.jpg");
        recipeManager.addRecipe("Beef Burger",
                "A classic American burger made with beef and your choice of toppings.",
                "['1 lb ground beef', '1 tsp salt', '1 tsp pepper', '1 tsp paprika', '1 tsp garlic powder', '1 tsp onion powder', '1 tsp Worcestershire sauce', '1 egg', '1/2 cup breadcrumbs', '4 burger buns', '4 slices cheddar cheese', '4 lettuce leaves', '4 tomato slices', '4 onion slices', '4 pickle slices', '4 tbsp ketchup', '4 tbsp mayonnaise', '4 tbsp mustard']",
                60,
                "['1. In a bowl, combine ground beef, salt, pepper, paprika, garlic powder, onion powder, Worcestershire sauce, egg, and breadcrumbs.', '2. Form into patties.', '3. Heat a pan over medium heat.', '4. Cook patties for 3-4 minutes per side.', '5. Place patties on burger buns and top with cheddar cheese, lettuce, tomato, onion, pickle, ketchup, mayonnaise, and mustard.', '6. Serve hot and enjoy!']",
                4.5,
                "/static/BeefBurger.jpg");
        recipeManager.addRecipe("Beef Stew",
                "A hearty stew made with beef, potatoes, and carrots.",
                "['1 lb beef', '1 onion', '2 cloves garlic', '2 carrots', '2 potatoes', '1 can tomato sauce', '1 cup beef broth', '1 tsp salt', '1 tsp pepper', '1 tsp paprika', '1 tsp thyme', '1 tsp rosemary', '1 tsp oregano']",
                120,
                "['1. In a pot, brown beef over medium heat.', '2. Add onion and garlic. Cook until onions are translucent.', '3. Add carrots, potatoes, tomato sauce, beef broth, salt, pepper, paprika, thyme, rosemary, and oregano.', '4. Cover and simmer for 1 hour.', '5. Serve hot and enjoy!']",
                4.5,
                "/static/BeefStew.jpg");
        recipeManager.addRecipe("Chicken Strips",
                "Crispy chicken strips served with your choice of dipping sauce.",
                "['2 chicken breasts', '1 cup flour', '1 tsp salt', '1 tsp pepper', '1 tsp paprika', '1 tsp garlic powder', '1 tsp onion powder', '2 eggs', '1 cup breadcrumbs', '1 cup vegetable oil']",
                60,
                "['1. Cut chicken breasts into strips.', '2. In a bowl, combine flour, salt, pepper, paprika, garlic powder, and onion powder.', '3. In a separate bowl, beat eggs.', '4. In a third bowl, add breadcrumbs.', '5. Coat chicken strips in flour mixture, then dip in egg, then coat with breadcrumbs.', '6. Heat vegetable oil in a pan over medium heat.', '7. Fry chicken strips until golden brown.', '8. Serve hot with your choice of dipping sauce and enjoy!']",
                4.0,
                "/static/ChickenStrips.jpg");
        recipeManager.addRecipe("Chicken Noodle Soup",
                "A classic soup made with chicken, vegetables, and noodles.",
                "['2 chicken breasts', '1 onion', '2 cloves garlic', '2 carrots', '2 celery stalks', '1 tsp salt', '1 tsp pepper', '1 tsp thyme', '1 tsp rosemary', '1 tsp oregano', '1 bay leaf', '4 cups chicken broth', '2 cups water', '1 cup egg noodles']",
                120,
                "['1. In a pot, combine chicken breasts, onion, garlic, carrots, celery, salt, pepper, thyme, rosemary, oregano, bay leaf, chicken broth, and water.', '2. Cover and simmer for 1 hour.', '3. Remove chicken breasts and shred.', '4. Add shredded chicken and egg noodles to the pot.', '5. Cover and simmer for 10 minutes.', '6. Serve hot and enjoy!']",
                4.5,
                "/static/ChickenNoodleSoup.jpg");
        recipeManager.addRecipe("Chicken Parmesan",
                "A classic Italian-American dish of breaded chicken cutlets baked with tomato sauce and mozzarella cheese.",
                "['4 chicken breasts', '1 cup breadcrumbs', '1 cup grated parmesan', '2 eggs', '1 cup tomato sauce', '1 cup mozzarella cheese']",
                60,
                "['1. Preheat oven to 400°F.', '2. In a bowl, combine breadcrumbs and parmesan.', '3. In a separate bowl, beat eggs.', '4. Dip chicken breasts in egg, then coat with breadcrumb mixture.', '5. Place chicken breasts on a baking sheet and bake for 20 minutes.', '6. Remove from oven and top with tomato sauce and mozzarella.', '7. Bake for another 10 minutes.', '8. Serve hot and enjoy!']",
                4.5,
                "/static/ChickenParmesan.jpg");
        recipeManager.addRecipe("Chicken Tikka Masala",
                "A popular Indian dish of roasted chicken chunks in a creamy tomato sauce.",
                "['2 chicken breasts', '1 cup plain yogurt', '1 tbsp lemon juice', '2 tsp cumin', '2 tsp garam masala', '1 tsp turmeric', '1 tsp paprika', '1 tsp salt', '1 tsp pepper', '1 tbsp butter', '1 onion', '2 cloves garlic', '1 tbsp ginger', '1 tsp cumin', '1 tsp garam masala', '1 tsp turmeric', '1 tsp paprika', '1 tsp salt', '1 tsp pepper', '1 can tomato sauce', '1 cup heavy cream']",
                120,
                "['1. In a bowl, combine yogurt, lemon juice, cumin, garam masala, turmeric, paprika, salt, and pepper.', '2. Add chicken breasts and coat with marinade. Cover and refrigerate for 1 hour.', '3. In a saucepan, melt butter over medium heat.', '4. Add onion, garlic, and ginger. Cook until onions are translucent.', '5. Add cumin, garam masala, turmeric, paprika, salt, and pepper. Cook for 1-2 minutes.', '6. Add tomato sauce and heavy cream. Stir until well combined.', '7. Add chicken breasts and simmer for 20 minutes.', '8. Serve hot with rice and enjoy!']",
                4.5,
                "/static/ChickenTikkaMasala.jpg");
        recipeManager.addRecipe("Chocolate Chip Cookies",
                "A classic American cookie made with chocolate chips.",
                "['1 cup butter', '1 cup brown sugar', '1 cup white sugar', '2 eggs', '1 tsp vanilla extract', '3 cups all-purpose flour', '1 tsp baking soda', '1 tsp salt', '2 cups chocolate chips']",
                60,
                "['1. Preheat oven to 350°F.', '2. In a bowl, cream together butter, brown sugar, and white sugar.', '3. Add eggs and vanilla extract. Mix until well combined.', '4. Add flour, baking soda, and salt. Mix until well combined.', '5. Fold in chocolate chips.', '6. Scoop dough onto a baking sheet.', '7. Bake for 10-12 minutes.', '8. Serve hot and enjoy!']",
                4.0,
                "/static/ChocolateChipCookies.jpg");
        recipeManager.addRecipe("Chocolate Lava Cake",
                "A decadent chocolate cake with a gooey chocolate center.",
                "['1 cup butter', '1 cup chocolate chips', '1 cup sugar', '4 eggs', '1 tsp vanilla extract', '1/2 cup all-purpose flour', '1/2 tsp salt']",
                60,
                "['1. Preheat oven to 425°F.', '2. In a saucepan, melt butter and chocolate chips over low heat.', '3. Remove from heat and stir in sugar.', '4. Add eggs and vanilla extract. Mix until well combined.', '5. Add flour and salt. Mix until well combined.', '6. Pour batter into greased ramekins.', '7. Bake for 10-12 minutes.', '8. Serve hot and enjoy!']",
                4.5,
                "/static/ChocolateLavaCake.jpg");
        recipeManager.addRecipe("Chocolate Milkshake",
                "A cold, sweet beverage made with chocolate ice cream and milk.",
                "['2 cups chocolate ice cream', '1 cup milk', '1/2 cup chocolate syrup']",
                10,
                "['1. Combine chocolate ice cream, milk, and chocolate syrup in a blender.', '2. Blend until smooth.', '3. Pour into a glass.', '4. Serve cold and enjoy!']",
                4.0,
                "/static/ChocolateMilkshake.jpg");
        recipeManager.saveRecipes();
        System.out.println("=====================================");
        // recipeManager.loadRecipes();
        // recipeManager.printRecipes();
    }

}
