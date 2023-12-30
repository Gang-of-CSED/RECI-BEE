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
//     private static final String RECIPE_FILE_PATH = "backend\\src\\main\\java\\com\\recibee\\backend\\data\\recipes.json";
    private static final String RECIPE_FILE_PATH = Paths.get("").toAbsolutePath().resolve("backend\\src\\main\\java\\com\\recibee\\backend\\data\\recipes.json").toString();
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

    public void addRecipe(String name, String description, String ingredients, String[] categories, int time,
            String steps, Double rate,
            String imagePath, String cuisine, int calories, String[] diet) {
        int id = recipes.size();
        RecipeModel recipe = new RecipeModel(id, name, description, ingredients, categories, time, steps, rate,
                imagePath, cuisine, calories, diet);
        recipes.put(id, recipe);
    }

    public void updateRecipe(int id, String name, String description, String ingredients, String[] categories, int time,
            String steps,
            Double rate,
            String imagePath, String cuisine, int calories, String[] diet) {
        RecipeModel recipe = new RecipeModel(id, name, description, ingredients, categories, time, steps, rate,
                imagePath, cuisine, calories, diet);
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
            Path path = Paths.get(RECIPE_FILE_PATH);
            Files.writeString(path, json);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void loadRecipes() {
        try {
            Path path = Paths.get(RECIPE_FILE_PATH);
            String content = Files.readString(path);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(content);
            for (JsonNode recipeNode : jsonNode) {
                RecipeModel recipe = new RecipeModel();
                recipe.setId(recipeNode.path("id").asInt());
                recipe.setName(recipeNode.path("name").asText());
                recipe.setDescription(recipeNode.path("description").asText());
                recipe.setIngredients(recipeNode.path("ingredients").asText());
                JsonNode categoriesNode = recipeNode.path("categories");
                if (categoriesNode.isArray()) {
                    String[] categories = new String[categoriesNode.size()];
                    for (int i = 0; i < categoriesNode.size(); i++) {
                        categories[i] = categoriesNode.get(i).asText();
                    }
                    recipe.setCategories(categories);
                }
                recipe.setTime(recipeNode.path("time").asInt());
                recipe.setSteps(recipeNode.path("steps").asText());
                recipe.setRate(recipeNode.path("rate").asDouble());
                recipe.setCover(recipeNode.path("cover").asText());
                recipe.setCuisine(recipeNode.path("cuisine").asText());
                recipe.setCalories(recipeNode.path("calories").asInt());
                JsonNode dietNode = recipeNode.path("diet");
                if (dietNode.isArray()) {
                    String[] diet = new String[dietNode.size()];
                    for (int i = 0; i < dietNode.size(); i++) {
                        diet[i] = dietNode.get(i).asText();
                    }
                    recipe.setDiet(diet);
                }

                RecipeManager.getInstance().addRecipe(recipe);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        RecipeManager recipeManager = RecipeManager.getInstance();
        // recipeManager.addRecipe("<p>Bashamel Pasta<p>",
        // "<p>A creamy pasta dish with homemade Bachamel sauce.<p>",
        // "<p>250g pasta</p><p>2 cups milk</p><p>4 tbsp butter</p><p>4 tbsp all-purpose
        // flour</p><p>Salt and pepper to taste</p>",
        // new String[] { "Lunch", "Dinner" },
        // 120,
        // "<p>Cook pasta according to package instructions.</p><p>In a saucepan, melt
        // butter over medium heat.</p><p>Stir in flour and cook for 2-3 minutes until
        // golden brown.</p><p>Gradually whisk in milk, stirring constantly until the
        // sauce thickens.</p><p>Season with salt and pepper.</p><p>Combine the cooked
        // pasta with the Béchamel sauce.</p><p>Pour into a baking dish and top with
        // cheddar cheese.</p><p>Bake at 350°F for 20 minutes.</p><p>Serve hot and
        // enjoy!</p>",
        // 4.0,
        // "/static/BashamelPasta.jpg");
        // recipeManager.addRecipe("<p>Mac and Cheese<p>",
        // "<p>A classic American dish of macaroni and cheese.<p>",
        // "<p>250g macaroni</p><p>2 cups milk</p><p>4 tbsp butter</p><p>4 tbsp
        // all-purpose flour</p><p>Salt and pepper to taste</p>",
        // new String[] { "Snacks", "Dinner" },
        // 30,
        // "<p>Cook macaroni according to package instructions.</p><p>In a saucepan,
        // melt butter over medium heat.</p><p>Stir in flour and cook for 2-3 minutes
        // until golden brown.</p><p>Gradually whisk in milk, stirring constantly until
        // the sauce thickens.</p><p>Season with salt and pepper.</p><p>Combine the
        // cooked macaroni with the Béchamel sauce.</p><p>Pour into a baking dish and
        // top with cheddar cheese.</p><p>Bake at 350°F for 20 minutes.</p><p>Serve hot
        // and enjoy!</p>",
        // 4.5,
        // "/static/MacAndCheese.jpg");
        // recipeManager.addRecipe("<p>Spaghetti and Meatballs<p>",
        // "<p>A classic Italian-American dish of spaghetti and meatballs.<p>",
        // "<p>250g spaghetti</p><p>1 lb ground beef</p><p>1 onion</p><p>2 cloves
        // garlic</p><p>1 egg</p><p>1/2 cup breadcrumbs</p><p>1 tsp salt</p><p>1 tsp
        // pepper</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion
        // powder</p><p>1 tsp oregano</p><p>1 tsp basil</p>",
        // new String[] { "Lunch", "Dinner" },
        // 90,
        // "<p>Cook spaghetti according to package instructions.</p><p>In a bowl,
        // combine ground beef, onion, garlic, egg, breadcrumbs, salt, pepper, paprika,
        // garlic powder, onion powder, oregano, and basil.</p><p>Form into
        // meatballs.</p><p>In a saucepan, heat tomato sauce over medium heat.</p><p>Add
        // meatballs and simmer for 20 minutes.</p><p>Serve hot and enjoy!</p>",
        // 4.5,
        // "/static/SpaghettiAndMeatballs.jpg");
        // recipeManager.addRecipe("<p>Beef Burger<p>",
        // "<p>A classic American dish of a beef burger.<p>",
        // "<p>1 lb ground beef</p><p>1 onion</p><p>2 cloves garlic</p><p>1
        // egg</p><p>1/2 cup breadcrumbs</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp
        // paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion powder</p><p>1 tsp
        // oregano</p><p>1 tsp basil</p>",
        // new String[] { "Lunch" },
        // 30,
        // "<p>In a bowl, combine ground beef, onion, garlic, egg, breadcrumbs, salt,
        // pepper, paprika, garlic powder, onion powder, oregano, and basil.</p><p>Form
        // into patties.</p><p>Grill patties over medium heat for 5 minutes per
        // side.</p><p>Serve hot and enjoy!</p>",
        // 4.0,
        // "/static/BeefBurger.jpg");
        // recipeManager.addRecipe("<p>Beef Stew<p>",
        // "<p>A classic American dish of beef stew.<p>",
        // "<p>1 lb beef</p><p>1 onion</p><p>2 carrots</p><p>2 celery stalks</p><p>2
        // cloves garlic</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp thyme</p><p>1
        // tsp rosemary</p><p>1 tsp oregano</p><p>1 bay leaf</p><p>4 cups beef
        // broth</p>",
        // new String[] { "Lunch", "Dinner" },
        // 120,
        // "<p>In a pot, combine beef, onion, carrots, celery, garlic, salt, pepper,
        // thyme, rosemary, oregano, bay leaf, and beef broth.</p><p>Cover and simmer
        // for 2 hours.</p><p>Serve hot and enjoy!</p>",
        // 4.5,
        // "/static/BeefStew.jpg");
        // recipeManager.addRecipe("<p>Chicken Strips<p>",
        // "<p>Crispy chicken strips served with your choice of dipping sauce.<p>",
        // "<p>2 chicken breasts</p><p>1 cup flour</p><p>1 tsp salt</p><p>1 tsp
        // pepper</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion
        // powder</p><p>1 tsp oregano</p><p>1 tsp basil</p><p>2 eggs</p><p>1 cup
        // breadcrumbs</p>",
        // new String[] { "Lunch", "Dinner" },
        // 60,
        // "<p>Preheat oven to 400°F.</p><p>In a bowl, combine flour, salt, pepper,
        // paprika, garlic powder, onion powder, oregano, and basil.</p><p>In a separate
        // bowl, beat eggs.</p><p>In a third bowl, add breadcrumbs.</p><p>Cut chicken
        // breasts into strips.</p><p>Dip chicken strips in flour mixture, then egg,
        // then breadcrumbs.</p><p>Place chicken strips on a baking sheet and bake for
        // 20 minutes.</p><p>Serve hot with your choice of dipping sauce and
        // enjoy!</p>",
        // 4.0,
        // "/static/ChickenStrips.jpg");
        // recipeManager.addRecipe("<p>Chicken Noodle Soup<p>",
        // "<p>A classic soup made with chicken, vegetables, and noodles.<p>",
        // "<p>2 chicken breasts</p><p>1 onion</p><p>2 carrots</p><p>2 celery
        // stalks</p><p>2 cloves garlic</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp
        // thyme</p><p>1 tsp rosemary</p><p>1 tsp oregano</p><p>1 bay leaf</p><p>4 cups
        // chicken broth</p><p>1 cup egg noodles</p>",
        // new String[] { "Lunch" },
        // 85,
        // "<p>In a pot, combine chicken, onion, carrots, celery, garlic, salt, pepper,
        // thyme, rosemary, oregano, bay leaf, and chicken broth.</p><p>Cover and simmer
        // for 1 hour.</p><p>Remove chicken breasts and shred with a fork.</p><p>Return
        // chicken to pot and add egg noodles.</p><p>Simmer for 10 minutes.</p><p>Serve
        // hot and enjoy!</p>",
        // 2.5,
        // "/static/ChickenNoodleSoup.jpg");
        // recipeManager.addRecipe("<p>Chicken Parmesan<p>",
        // "<p>A classic Italian-American dish of breaded chicken cutlets baked with
        // tomato sauce and mozzarella cheese.<p>",
        // "<p>2 chicken breasts</p><p>1 cup flour</p><p>1 tsp salt</p><p>1 tsp
        // pepper</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion
        // powder</p><p>1 tsp oregano</p><p>1 tsp basil</p><p>2 eggs</p><p>1 cup
        // breadcrumbs</p><p>1 cup tomato sauce</p><p>1 cup mozzarella cheese</p>",
        // new String[] { "Lunch", "Dinner" },
        // 60,
        // "<p>Preheat oven to 400°F.</p><p>In a bowl, combine flour, salt, pepper,
        // paprika, garlic powder, onion powder, oregano, and basil.</p><p>In a separate
        // bowl, beat eggs.</p><p>In a third bowl, add breadcrumbs.</p><p>Cut chicken
        // breasts in half lengthwise.</p><p>Dip chicken breasts in flour mixture, then
        // egg, then breadcrumbs.</p><p>Place chicken breasts on a baking sheet and bake
        // for 20 minutes.</p><p>Top chicken breasts with tomato sauce and mozzarella
        // cheese.</p><p>Bake for an additional 10 minutes.</p><p>Serve hot and
        // enjoy!</p>",
        // 4.5,
        // "/static/ChickenParmesan.jpg");
        // recipeManager.addRecipe("<p>Chicken Tikka Masala<p>",
        // "<p>A popular Indian dish of roasted chicken chunks in a creamy tomato
        // sauce.<p>",
        // "<p>2 chicken breasts</p><p>1 onion</p><p>2 cloves garlic</p><p>1 tsp
        // salt</p><p>1 tsp pepper</p><p>1 tsp paprika</p><p>1 tsp cumin</p><p>1 tsp
        // coriander</p><p>1 tsp turmeric</p><p>1 tsp garam masala</p><p>1 tsp chili
        // powder</p><p>1 tsp ginger</p><p>1 tsp garlic powder</p><p>1 tsp onion
        // powder</p><p>1 tsp cayenne pepper</p><p>1 tsp cinnamon</p><p>1 tsp
        // cardamom</p><p>1 tsp nutmeg</p><p>1 tsp cloves</p><p>1 tsp fenugreek</p><p>1
        // tsp mustard seeds</p><p>1 tsp fennel seeds</p><p>1 tsp curry leaves</p><p>1
        // tsp tomato paste</p><p>1 cup tomato sauce</p><p>1 cup heavy cream</p>",
        // new String[] { "Lunch" },
        // 90,
        // "<p>In a bowl, combine salt, pepper, paprika, cumin, coriander, turmeric,
        // garam masala, chili powder, ginger, garlic powder, onion powder, cayenne
        // pepper, cinnamon, cardamom, nutmeg, cloves, fenugreek, mustard seeds, fennel
        // seeds, and curry leaves.</p><p>Cut chicken breasts into chunks and coat with
        // spice mixture.</p><p>In a saucepan, heat oil over medium heat.</p><p>Add
        // onion and garlic and cook until softened.</p><p>Add chicken and cook until
        // browned on all sides.</p><p>Add tomato paste, tomato sauce, and heavy
        // cream.</p><p>Simmer for 30 minutes.</p><p>Serve hot and enjoy!</p>",
        // 3.5,
        // "/static/ChickenTikkaMasala.jpg");
        // recipeManager.addRecipe("<p>Chocolate Chip Cookies<p>",
        // "<p>A classic American cookie made with chocolate chips.<p>",
        // "<p>1 cup butter</p><p>1 cup sugar</p><p>1 cup brown sugar</p><p>2
        // eggs</p><p>1 tsp vanilla extract</p><p>2 cups all-purpose flour</p><p>1 tsp
        // baking soda</p><p>1 tsp salt</p><p>2 cups chocolate chips</p>",
        // new String[] { "Snacks", "Dessert", "Breakfast" },
        // 30,
        // "<p>Preheat oven to 375°F.</p><p>In a bowl, combine butter, sugar, and brown
        // sugar. Mix until well combined.</p><p>Add eggs and vanilla extract. Mix until
        // well combined.</p><p>Add flour, baking soda, and salt. Mix until well
        // combined.</p><p>Stir in chocolate chips.</p><p>Drop spoonfuls of dough onto a
        // baking sheet.</p><p>Bake for 10-12 minutes.</p><p>Serve hot and enjoy!</p>",
        // 5.0,
        // "/static/ChocolateChipCookies.jpg");
        // recipeManager.addRecipe("<p>Chocolate Lava Cake<p>",
        // "<p>A decadent chocolate cake with a gooey chocolate center.<p>",
        // "<p>1 cup butter</p><p>1 cup chocolate chips</p><p>1 cup sugar</p><p>1 cup
        // brown sugar</p><p>4 eggs</p><p>1 tsp vanilla extract</p><p>1 cup all-purpose
        // flour</p><p>1 tsp salt</p>",
        // new String[] { "Dessert" },
        // 30,
        // "<p>Preheat oven to 375°F.</p><p>In a saucepan, melt butter and chocolate
        // chips over medium heat.</p><p>In a bowl, combine sugar, brown sugar, eggs,
        // and vanilla extract. Mix until well combined.</p><p>Add flour and salt. Mix
        // until well combined.</p><p>Stir in melted chocolate mixture.</p><p>Pour
        // batter into a greased muffin tin.</p><p>Bake for 10-12 minutes.</p><p>Serve
        // hot and enjoy!</p>",
        // 4.5,
        // "/static/ChocolateLavaCake.jpg");
        // recipeManager.addRecipe("<p>Chocolate Milkshake<p>",
        // "<p>A cold, sweet beverage made with chocolate ice cream and milk.<p>",
        // "<p>2 cups chocolate ice cream</p><p>1 cup milk</p><p>1/2 cup chocolate
        // syrup</p>",
        // new String[] { "Drinks", "Breakfast" },
        // 10,
        // "<p>Combine chocolate ice cream, milk, and chocolate syrup in a
        // blender.</p><p>Blend until smooth.</p><p>Serve cold and enjoy!</p>",
        // 4.0,
        // "/static/ChocolateMilkshake.jpg");
//         recipeManager.addRecipe("<p>French Toast<p>",
//                 "<p>A classic American breakfast dish of bread soaked in egg and milk and fried.<p>",
//                 "<p>4 slices bread</p><p>2 eggs</p><p>1 cup milk</p><p>1 tsp vanilla extract</p><p>1 tsp cinnamon</p><p>1 tsp nutmeg</p><p>1 tsp salt</p>",
//                 new String[] { "Breakfast" },
//                 15,
//                 "<p>In a bowl, combine eggs, milk, vanilla extract, cinnamon, nutmeg, and salt.</p><p>Dip bread slices in egg mixture.</p><p>Fry bread slices in a pan over medium heat until golden brown on both sides.</p><p>Serve hot and enjoy!</p>",
//                 4.5,
//                 "/static/FrenchToast.jpg");
//         recipeManager.addRecipe("<p>Grilled Cheese<p>",
//                 "<p>A classic American dish of melted cheese between two slices of bread.<p>",
//                 "<p>4 slices bread</p><p>2 slices cheddar cheese</p><p>2 slices mozzarella cheese</p><p>2 slices provolone cheese</p><p>2 tbsp butter</p>",
//                 new String[] { "Breakfast" },
//                 10,
//                 "<p>Butter one side of each slice of bread.</p><p>Place one slice of bread, butter side down, in a pan over medium heat.</p><p>Top with cheddar cheese, mozzarella cheese, and provolone cheese.</p><p>Top with remaining slice of bread, butter side up.</p><p>Cook until golden brown on both sides.</p><p>Serve hot and enjoy!</p>",
//                 4.5,
//                 "/static/GrilledCheese.jpg");
//         recipeManager.addRecipe("<p>Hot Chocolate<p>",
//                 "<p>A hot beverage made with chocolate and milk.<p>",
//                 "<p>2 cups milk</p><p>1/2 cup chocolate chips</p><p>1 tsp vanilla extract</p>",
//                 new String[] { "Drinks", "Breakfast" },
//                 10,
//                 "<p>In a saucepan, heat milk over medium heat.</p><p>Add chocolate chips and vanilla extract.</p><p>Stir until chocolate chips are melted.</p><p>Serve hot and enjoy!</p>",
//                 4.0,
//                 "/static/HotChocolate.jpg");
//         recipeManager.addRecipe("<p>Ice Cream<p>",
//                 "<p>A cold, sweet dessert made with milk and sugar.<p>",
//                 "<p>2 cups milk</p><p>1 cup sugar</p><p>1 tsp vanilla extract</p>",
//                 new String[] { "Dessert" },
//                 10,
//                 "<p>In a bowl, combine milk, sugar, and vanilla extract.</p><p>Stir until sugar is dissolved.</p><p>Pour mixture into an ice cream maker and churn according to manufacturer's instructions.</p><p>Serve cold and enjoy!</p>",
//                 4.0,
//                 "/static/IceCream.jpg");
//         recipeManager.addRecipe("<p>Falafel<p>",
//                 "<p>A popular Middle Eastern dish of fried chickpea balls.<p>",
//                 "<p>1 cup dried chickpeas</p><p>1 onion</p><p>2 cloves garlic</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp cumin</p><p>1 tsp coriander</p><p>1 tsp paprika</p><p>1 tsp baking soda</p><p>1 tsp lemon juice</p><p>1 cup parsley</p><p>1 cup cilantro</p>",
//                 new String[] { "Breakfast", "Dinner" },
//                 120,
//                 "<p>Soak chickpeas in water overnight.</p><p>Drain chickpeas and place in a food processor.</p><p>Add onion, garlic, salt, pepper, cumin, coriander, paprika, baking soda, lemon juice, parsley, and cilantro.</p><p>Pulse until mixture is finely ground.</p><p>Form mixture into balls.</p><p>Fry balls in oil over medium heat until golden brown.</p><p>Serve hot and enjoy!</p>",
//                 4.0,
//                 "/static/Falafel.jpg");
//         recipeManager.addRecipe("<p>Lasagna<p>",
//                 "<p>A classic Italian dish of layered pasta, meat, and cheese.<p>",
//                 "<p>1 lb ground beef</p><p>1 onion</p><p>2 cloves garlic</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp oregano</p><p>1 tsp basil</p><p>1 tsp thyme</p><p>1 tsp rosemary</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion powder</p><p>1 tsp cayenne pepper</p><p>1 tsp cinnamon</p><p>1 tsp nutmeg</p><p>1 tsp cloves</p><p>1 tsp fennel seeds</p><p>1 tsp tomato paste</p><p>1 cup tomato sauce</p><p>1 cup ricotta cheese</p><p>1 cup mozzarella cheese</p><p>1 cup parmesan cheese</p><p>1 cup lasagna noodles</p>",
//                 new String[] { "Lunch", "Dinner" },
//                 120,
//                 "<p>In a saucepan, heat oil over medium heat.</p><p>Add onion and garlic and cook until softened.</p><p>Add ground beef and cook until browned.</p><p>Add salt, pepper, oregano, basil, thyme, rosemary, paprika, garlic powder, onion powder, cayenne pepper, cinnamon, nutmeg, cloves, fennel seeds, tomato paste, and tomato sauce.</p><p>Cover and simmer for 30 minutes.</p><p>In a baking dish, layer meat sauce, ricotta cheese, mozzarella cheese, parmesan cheese, and lasagna noodles.</p><p>Repeat layers until baking dish is full.</p><p>Bake at 350°F for 30 minutes.</p><p>Serve hot and enjoy!</p>",
//                 4.5,
//                 "/static/Lasagna.jpg");
//         recipeManager.addRecipe("<p>Meatloaf<p>",
//                 "<p>A classic American dish of ground beef and spices baked in a loaf shape.<p>",
//                 "<p>1 lb ground beef</p><p>1 onion</p><p>2 cloves garlic</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp oregano</p><p>1 tsp basil</p><p>1 tsp thyme</p><p>1 tsp rosemary</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion powder</p><p>1 tsp cayenne pepper</p><p>1 tsp cinnamon</p><p>1 tsp nutmeg</p><p>1 tsp cloves</p><p>1 tsp fennel seeds</p><p>1 tsp tomato paste</p><p>1 cup tomato sauce</p><p>1 cup breadcrumbs</p><p>1 egg</p>",
//                 new String[] { "Lunch", "Dinner" },
//                 90,
//                 "<p>In a bowl, combine ground beef, onion, garlic, salt, pepper, oregano, basil, thyme, rosemary, paprika, garlic powder, onion powder, cayenne pepper, cinnamon, nutmeg, cloves, fennel seeds, tomato paste, tomato sauce, breadcrumbs, and egg.</p><p>Form into a loaf shape.</p><p>Bake at 350°F for 1 hour.</p><p>Serve hot and enjoy!</p>",
//                 4.5,
//                 "/static/Meatloaf.jpg");
//         recipeManager.addRecipe("<p>French Fries<p>",
//                 "<p>A classic American side dish of fried potatoes.<p>",
//                 "<p>2 potatoes</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion powder</p><p>1 tsp oregano</p><p>1 tsp basil</p>",
//                 new String[] { "Lunch", "Dinner", "Snacks" },
//                 30,
//                 "<p>Cut potatoes into strips.</p><p>In a bowl, combine salt, pepper, paprika, garlic powder, onion powder, oregano, and basil.</p><p>Toss potatoes in spice mixture.</p><p>Fry potatoes in oil over medium heat until golden brown.</p><p>Serve hot and enjoy!</p>",
//                 4.0,
//                 "/static/FrenchFries.jpg");
//         recipeManager.addRecipe("<p>Onion Rings<p>",
//                 "<p>A classic American side dish of fried onion rings.<p>",
//                 "<p>2 onions</p><p>1 cup flour</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion powder</p><p>1 tsp oregano</p><p>1 tsp basil</p><p>2 eggs</p><p>1 cup breadcrumbs</p>",
//                 new String[] { "Lunch", "Dinner", "Snacks" },
//                 30,
//                 "<p>Cut onions into rings.</p><p>In a bowl, combine flour, salt, pepper, paprika, garlic powder, onion powder, oregano, and basil.</p><p>In a separate bowl, beat eggs.</p><p>In a third bowl, add breadcrumbs.</p><p>Dip onion rings in flour mixture, then egg, then breadcrumbs.</p><p>Fry onion rings in oil over medium heat until golden brown.</p><p>Serve hot and enjoy!</p>",
//                 2.5,
//                 "/static/OnionRings.jpg");
//         recipeManager.addRecipe("<p>Pancakes<p>",
//                 "<p>A classic American breakfast dish of fried batter.<p>",
//                 "<p>2 cups flour</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion powder</p><p>1 tsp oregano</p><p>1 tsp basil</p><p>2 eggs</p><p>1 cup milk</p><p>1 tsp vanilla extract</p>",
//                 new String[] { "Breakfast" },
//                 30,
//                 "<p>In a bowl, combine flour, salt, pepper, paprika, garlic powder, onion powder, oregano, and basil.</p><p>In a separate bowl, beat eggs.</p><p>Add milk and vanilla extract to eggs.</p><p>Add egg mixture to flour mixture.</p><p>Stir until well combined.</p><p>Pour batter onto a greased pan over medium heat.</p><p>Cook until golden brown on both sides.</p><p>Serve hot and enjoy!</p>",
//                 5.0,
//                 "/static/Pancakes.jpg");
//         recipeManager.addRecipe("<p>Molten Cake</p>",
//                 "<p>A decadent chocolate cake with a gooey chocolate center.<p>",
//                 "<p>1 cup butter</p><p>1 cup chocolate chips</p><p>1 cup sugar</p><p>1 cup brown sugar</p><p>4 eggs</p><p>1 tsp vanilla extract</p><p>1 cup all-purpose flour</p><p>1 tsp salt</p>",
//                 new String[] { "Dessert" },
//                 30,
//                 "<p>Preheat oven to 375°F.</p><p>In a saucepan, melt butter and chocolate chips over medium heat.</p><p>In a bowl, combine sugar, brown sugar, eggs, and vanilla extract. Mix until well combined.</p><p>Add flour and salt. Mix until well combined.</p><p>Stir in melted chocolate mixture.</p><p>Pour batter into a greased muffin tin.</p><p>Bake for 10-12 minutes.</p><p>Serve hot and enjoy!</p>",
//                 4.5,
//                 "/static/MoltenCake.jpg");
//         recipeManager.addRecipe("<p>Sherbet</p>",
//                 "<p>A cold, sweet dessert made with fruit and sugar.<p>",
//                 "<p>2 cups fruit</p><p>1 cup sugar</p><p>1 tsp vanilla extract</p>",
//                 new String[] { "Dessert" },
//                 10,
//                 "<p>In a bowl, combine fruit, sugar, and vanilla extract.</p><p>Stir until sugar is dissolved.</p><p>Pour mixture into an ice cream maker and churn according to manufacturer's instructions.</p><p>Serve cold and enjoy!</p>",
//                 4.0,
//                 "/static/Sherbet.jpg");
//         recipeManager.addRecipe("<p>Shrimp Scampi</p>",
//                 "<p>A classic Italian dish of shrimp in a garlic butter sauce.<p>",
//                 "<p>1 lb shrimp</p><p>1 onion</p><p>2 cloves garlic</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp oregano</p><p>1 tsp basil</p><p>1 tsp thyme</p><p>1 tsp rosemary</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion powder</p><p>1 tsp cayenne pepper</p><p>1 tsp cinnamon</p><p>1 tsp nutmeg</p><p>1 tsp cloves</p><p>1 tsp fennel seeds</p><p>1 tsp tomato paste</p><p>1 cup tomato sauce</p><p>1 cup ricotta cheese</p><p>1 cup mozzarella cheese</p><p>1 cup parmesan cheese</p><p>1 cup lasagna noodles</p>",
//                 new String[] { "Lunch", "Dinner" },
//                 120,
//                 "<p>In a saucepan, heat oil over medium heat.</p><p>Add onion and garlic and cook until softened.</p><p>Add shrimp and cook until pink.</p><p>Add salt, pepper, oregano, basil, thyme, rosemary, paprika, garlic powder, onion powder, cayenne pepper, cinnamon, nutmeg, cloves, fennel seeds, tomato paste, and tomato sauce.</p><p>Cover and simmer for 30 minutes.</p><p>Serve hot and enjoy!</p>",
//                 4.5,
//                 "/static/ShrimpScampi.jpg");
//         recipeManager.addRecipe("<p>Cheese Lovers Pizza</p>",
//                 "<p>A classic Italian dish of pizza with cheese.<p>",
//                 "<p>1 lb ground beef</p><p>1 onion</p><p>2 cloves garlic</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp oregano</p><p>1 tsp basil</p><p>1 tsp thyme</p><p>1 tsp rosemary</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion powder</p><p>1 tsp cayenne pepper</p><p>1 tsp cinnamon</p><p>1 tsp nutmeg</p><p>1 tsp cloves</p><p>1 tsp fennel seeds</p><p>1 tsp tomato paste</p><p>1 cup tomato sauce</p><p>1 cup ricotta cheese</p><p>1 cup mozzarella cheese</p><p>1 cup parmesan cheese</p><p>1 cup lasagna noodles</p>",
//                 new String[] { "Lunch", "Dinner" },
//                 120,
//                 "<p>In a saucepan, heat oil over medium heat.</p><p>Add onion and garlic and cook until softened.</p><p>Add ground beef and cook until browned.</p><p>Add salt, pepper, oregano, basil, thyme, rosemary, paprika, garlic powder, onion powder, cayenne pepper, cinnamon, nutmeg, cloves, fennel seeds, tomato paste, and tomato sauce.</p><p>Cover and simmer for 30 minutes.</p><p>In a baking dish, layer meat sauce, ricotta cheese, mozzarella cheese, parmesan cheese, and lasagna noodles.</p><p>Repeat layers until baking dish is full.</p><p>Bake at 350°F for 30 minutes.</p><p>Serve hot and enjoy!</p>",
//                 4.5,
//                 "/static/CheeseLoversPizza.jpg");
//         recipeManager.addRecipe("<p>Chicken Alfredo</p>",
//                 "<p>A classic Italian dish of pasta with chicken and a creamy sauce.<p>",
//                 "<p>1 lb ground beef</p><p>1 onion</p><p>2 cloves garlic</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp oregano</p><p>1 tsp basil</p><p>1 tsp thyme</p><p>1 tsp rosemary</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion powder</p><p>1 tsp cayenne pepper</p><p>1 tsp cinnamon</p><p>1 tsp nutmeg</p><p>1 tsp cloves</p><p>1 tsp fennel seeds</p><p>1 tsp tomato paste</p><p>1 cup tomato sauce</p><p>1 cup ricotta cheese</p><p>1 cup mozzarella cheese</p><p>1 cup parmesan cheese</p><p>1 cup lasagna noodles</p>",
//                 new String[] { "Lunch", "Dinner" },
//                 120,
//                 "<p>In a saucepan, heat oil over medium heat.</p><p>Add onion and garlic and cook until softened.</p><p>Add ground beef and cook until browned.</p><p>Add salt, pepper, oregano, basil, thyme, rosemary, paprika, garlic powder, onion powder, cayenne pepper, cinnamon, nutmeg, cloves, fennel seeds, tomato paste, and tomato sauce.</p><p>Cover and simmer for 30 minutes.</p><p>In a baking dish, layer meat sauce, ricotta cheese, mozzarella cheese, parmesan cheese, and lasagna noodles.</p><p>Repeat layers until baking dish is full.</p><p>Bake at 350°F for 30 minutes.</p><p>Serve hot and enjoy!</p>",
//                 4.5,
//                 "/static/ChickenAlfredo.jpg");
//         recipeManager.addRecipe("<p>Chicken Curry</p>",
//                 "<p>A popular Indian dish of chicken in a spicy sauce.<p>",
//                 "<p>1 lb ground beef</p><p>1 onion</p><p>2 cloves garlic</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp oregano</p><p>1 tsp basil</p><p>1 tsp thyme</p><p>1 tsp rosemary</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion powder</p><p>1 tsp cayenne pepper</p><p>1 tsp cinnamon</p><p>1 tsp nutmeg</p><p>1 tsp cloves</p><p>1 tsp fennel seeds</p><p>1 tsp tomato paste</p><p>1 cup tomato sauce</p><p>1 cup ricotta cheese</p><p>1 cup mozzarella cheese</p><p>1 cup parmesan cheese</p><p>1 cup lasagna noodles</p>",
//                 new String[] { "Lunch", "Dinner" },
//                 120,
//                 "<p>In a saucepan, heat oil over medium heat.</p><p>Add onion and garlic and cook until softened.</p><p>Add ground beef and cook until browned.</p><p>Add salt, pepper, oregano, basil, thyme, rosemary, paprika, garlic powder, onion powder, cayenne pepper, cinnamon, nutmeg, cloves, fennel seeds, tomato paste, and tomato sauce.</p><p>Cover and simmer for 30 minutes.</p><p>In a baking dish, layer meat sauce, ricotta cheese, mozzarella cheese, parmesan cheese, and lasagna noodles.</p><p>Repeat layers until baking dish is full.</p><p>Bake at 350°F for 30 minutes.</p><p>Serve hot and enjoy!</p>",
//                 4.5,
//                 "/static/ChickenCurry.jpg");
//         recipeManager.addRecipe("<p>Boiled Eggs</p>",
//                 "<p>A classic American dish of boiled eggs.<p>",
//                 "<p>2 eggs</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion powder</p><p>1 tsp oregano</p><p>1 tsp basil</p>",
//                 new String[] { "Breakfast" },
//                 10,
//                 "<p>Place eggs in a pot and cover with water.</p><p>Bring water to a boil.</p><p>Remove from heat and let sit for 10 minutes.</p><p>Drain water and place eggs in an ice bath.</p><p>Peel eggs and serve hot.</p><p>Serve hot and enjoy!</p>",
//                 4.0,
//                 "/static/BoiledEggs.jpg");
//         recipeManager.addRecipe("<p>Apple Pie</p>",
//                 "<p>A classic American dessert of apple pie.<p>",
//                 "<p>2 apples</p><p>1 cup sugar</p><p>1 tsp vanilla extract</p><p>1 tsp cinnamon</p><p>1 tsp nutmeg</p><p>1 tsp cloves</p><p>1 tsp fennel seeds</p><p>1 tsp lemon juice</p><p>1 cup flour</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion powder</p><p>1 tsp oregano</p><p>1 tsp basil</p>",
//                 new String[] { "Dessert" },
//                 60,
//                 "<p>Preheat oven to 375°F.</p><p>In a bowl, combine apples, sugar, vanilla extract, cinnamon, nutmeg, cloves, fennel seeds, and lemon juice.</p><p>Pour mixture into a pie crust.</p><p>In a bowl, combine flour, salt, pepper, paprika, garlic powder, onion powder, oregano, and basil.</p><p>Sprinkle mixture over pie.</p><p>Bake for 30 minutes.</p><p>Serve hot and enjoy!</p>",
//                 1.5,
//                 "/static/ApplePie.jpg");
//         recipeManager.addRecipe("<p>Ice Tea</p>",
//                 "<p>A cold, sweet beverage made with tea and sugar.<p>",
//                 "<p>2 cups tea</p><p>1 cup sugar</p><p>1 tsp vanilla extract</p>",
//                 new String[] { "Drinks", "Breakfast" },
//                 10,
//                 "<p>In a bowl, combine tea, sugar, and vanilla extract.</p><p>Stir until sugar is dissolved.</p><p>Pour mixture into an ice cream maker and churn according to manufacturer's instructions.</p><p>Serve cold and enjoy!</p>",
//                 4.0,
//                 "/static/IceTea.jpg");
//         recipeManager.addRecipe("<p>Brownies</p>",
//                 "<p>A classic American dessert of brownies.<p>",
//                 "<p>1 cup butter</p><p>1 cup chocolate chips</p><p>1 cup sugar</p><p>1 cup brown sugar</p><p>4 eggs</p><p>1 tsp vanilla extract</p><p>1 cup all-purpose flour</p><p>1 tsp salt</p>",
//                 new String[] { "Dessert" },
//                 30,
//                 "<p>Preheat oven to 375°F.</p><p>In a saucepan, melt butter and chocolate chips over medium heat.</p><p>In a bowl, combine sugar, brown sugar, eggs, and vanilla extract. Mix until well combined.</p><p>Add flour and salt. Mix until well combined.</p><p>Stir in melted chocolate mixture.</p><p>Pour batter into a greased muffin tin.</p><p>Bake for 10-12 minutes.</p><p>Serve hot and enjoy!</p>",
//                 4.5,
//                 "/static/Brownies.jpg");
//         recipeManager.addRecipe("<p>Chocolate Cake</p>",
//                 "<p>A decadent chocolate cake with a gooey chocolate center.<p>",
//                 "<p>1 cup butter</p><p>1 cup chocolate chips</p><p>1 cup sugar</p><p>1 cup brown sugar</p><p>4 eggs</p><p>1 tsp vanilla extract</p><p>1 cup all-purpose flour</p><p>1 tsp salt</p>",
//                 new String[] { "Dessert" },
//                 30,
//                 "<p>Preheat oven to 375°F.</p><p>In a saucepan, melt butter and chocolate chips over medium heat.</p><p>In a bowl, combine sugar, brown sugar, eggs, and vanilla extract. Mix until well combined.</p><p>Add flour and salt. Mix until well combined.</p><p>Stir in melted chocolate mixture.</p><p>Pour batter into a greased muffin tin.</p><p>Bake for 10-12 minutes.</p><p>Serve hot and enjoy!</p>",
//                 4.5,
//                 "/static/ChocolateCake.jpg");
//         recipeManager.addRecipe("<p>Chocolate Mousse</p>",
//                 "<p>A cold, sweet dessert made with chocolate and cream.<p>",
//                 "<p>2 cups cream</p><p>1 cup chocolate chips</p><p>1 tsp vanilla extract</p>",
//                 new String[] { "Dessert" },
//                 10,
//                 "<p>In a saucepan, heat cream over medium heat.</p><p>Add chocolate chips and vanilla extract.</p><p>Stir until chocolate chips are melted.</p><p>Serve hot and enjoy!</p>",
//                 2.0,
//                 "/static/ChocolateMousse.jpg");
//         recipeManager.addRecipe("<p>Motzerella Sticks</p>",
//                 "<p>A classic American side dish of fried cheese sticks.<p>",
//                 "<p>2 cups cheese</p><p>1 cup flour</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion powder</p><p>1 tsp oregano</p><p>1 tsp basil</p><p>2 eggs</p><p>1 cup breadcrumbs</p>",
//                 new String[] { "Lunch", "Dinner", "Snacks" },
//                 30,
//                 "<p>Cut cheese into sticks.</p><p>In a bowl, combine flour, salt, pepper, paprika, garlic powder, onion powder, oregano, and basil.</p><p>In a separate bowl, beat eggs.</p><p>In a third bowl, add breadcrumbs.</p><p>Dip cheese sticks in flour mixture, then egg, then breadcrumbs.</p><p>Fry cheese sticks in oil over medium heat until golden brown.</p><p>Serve hot and enjoy!</p>",
//                 3.5,
//                 "/static/MotzerellaSticks.jpg");
//         recipeManager.addRecipe("<p>Shaweema</p>",
//                 "<p>A popular Middle Eastern dish of spiced meat in a pita.<p>",
//                 "<p>1 lb ground beef</p><p>1 onion</p><p>2 cloves garlic</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp oregano</p><p>1 tsp basil</p><p>1 tsp thyme</p><p>1 tsp rosemary</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion powder</p><p>1 tsp cayenne pepper</p><p>1 tsp cinnamon</p><p>1 tsp nutmeg</p><p>1 tsp cloves</p><p>1 tsp fennel seeds</p><p>1 tsp tomato paste</p><p>1 cup tomato sauce</p><p>1 cup ricotta cheese</p><p>1 cup mozzarella cheese</p><p>1 cup parmesan cheese</p><p>1 cup lasagna noodles</p>",
//                 new String[] { "Lunch", "Dinner" },
//                 120,
//                 "<p>In a saucepan, heat oil over medium heat.</p><p>Add onion and garlic and cook until softened.</p><p>Add ground beef and cook until browned.</p><p>Add salt, pepper, oregano, basil, thyme, rosemary, paprika, garlic powder, onion powder, cayenne pepper, cinnamon, nutmeg, cloves, fennel seeds, tomato paste, and tomato sauce.</p><p>Cover and simmer for 30 minutes.</p><p>In a baking dish, layer meat sauce, ricotta cheese, mozzarella cheese, parmesan cheese, and lasagna noodles.</p><p>Repeat layers until baking dish is full.</p><p>Bake at 350°F for 30 minutes.</p><p>Serve hot and enjoy!</p>",
//                 4.5,
//                 "/static/Shaweema.jpg");
//         recipeManager.addRecipe("<p>Pickles</p>",
//                 "<p>A classic American side dish of pickles.<p>",
//                 "<p>2 cucumbers</p><p>1 cup vinegar</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion powder</p><p>1 tsp oregano</p><p>1 tsp basil</p>",
//                 new String[] { "Lunch", "Dinner", "Snacks" },
//                 30,
//                 "<p>Cut cucumbers into slices.</p><p>In a bowl, combine vinegar, salt, pepper, paprika, garlic powder, onion powder, oregano, and basil.</p><p>Add cucumbers to vinegar mixture.</p><p>Let sit for 30 minutes.</p><p>Serve cold and enjoy!</p>",
//                 4.0,
//                 "/static/Pickles.jpg");
//         recipeManager.addRecipe("<p>Croissant</p>",
//                 "<p>A classic French dish of croissant.<p>",
//                 "<p>2 cups flour</p><p>1 tsp salt</p><p>1 tsp pepper</p><p>1 tsp paprika</p><p>1 tsp garlic powder</p><p>1 tsp onion powder</p><p>1 tsp oregano</p><p>1 tsp basil</p><p>2 eggs</p><p>1 cup milk</p><p>1 tsp vanilla extract</p>",
//                 new String[] { "Breakfast" },
//                 30,
//                 "<p>In a bowl, combine flour, salt, pepper, paprika, garlic powder, onion powder, oregano, and basil.</p><p>In a separate bowl, beat eggs.</p><p>Add milk and vanilla extract to eggs.</p><p>Add egg mixture to flour mixture.</p><p>Stir until well combined.</p><p>Pour batter onto a greased pan over medium heat.</p><p>Cook until golden brown on both sides.</p><p>Serve hot and enjoy!</p>",
//                 5.0,
//                 "/static/Croissant.jpg");
//         recipeManager.addRecipe("<p>Donuts</p>",
//                 "<p>A classic American dessert of donuts.<p>",
//                 "<p>1 cup butter</p><p>1 cup chocolate chips</p><p>1 cup sugar</p><p>1 cup brown sugar</p><p>4 eggs</p><p>1 tsp vanilla extract</p><p>1 cup all-purpose flour</p><p>1 tsp salt</p>",
//                 new String[] { "Dessert", "Breakfast" },
//                 30,
//                 "<p>Preheat oven to 375°F.</p><p>In a saucepan, melt butter and chocolate chips over medium heat.</p><p>In a bowl, combine sugar, brown sugar, eggs, and vanilla extract. Mix until well combined.</p><p>Add flour and salt. Mix until well combined.</p><p>Stir in melted chocolate mixture.</p><p>Pour batter into a greased muffin tin.</p><p>Bake for 10-12 minutes.</p><p>Serve hot and enjoy!</p>",
//                 4.5,
//                 "/static/Donuts.jpg");
//         recipeManager.addRecipe("<p>Cafe Latte</p>",
//                 "<p>A hot beverage made with coffee and milk.<p>",
//                 "<p>2 cups coffee</p><p>1/2 cup milk</p><p>1/2 cup chocolate syrup</p>",
//                 new String[] { "Drinks", "Breakfast" },
//                 10,
//                 "<p>Combine coffee, milk, and chocolate syrup in a blender.</p><p>Blend until smooth.</p><p>Serve cold and enjoy!</p>",
//                 4.0,
//                 "/static/CafeLatte.jpg");
//         recipeManager.saveRecipes();
//         System.out.println("=====================================");
//         // recipeManager.loadRecipes();
//         // recipeManager.printRecipes();
    }

}
