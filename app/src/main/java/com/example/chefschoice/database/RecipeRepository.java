package com.example.chefschoice.database;


import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.*;
import java.util.ArrayList;
import java.util.UUID;


public class RecipeRepository {

    private static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String RECIPE_COLLECTION = "RecipeBook";
    private DocumentReference recipeRef;

    public static final String KEY_RECIPENAME = "name";
    public static final String KEY_RECIPE_INGREDIENTS = "ingredients";
    public static final String KEY_RECIPE_INSTRUCTIONS = "instructions";
    public static final String KEY_RECIPE_FOOD_CATEGORY = "foodCategory";

    public static final ArrayList<Recipe> ALL_RECIPIES = new ArrayList<>();


    public static Task<Void> addRecipe(Recipe recipe) {
                return db.collection("RecipeBook").document(UUID.randomUUID().toString()).set(recipe);
    }

    public static Task<Void> editRecipe(Recipe recipe, String id) {
        return db.collection("RecipeBook").document(id).set(recipe);
    }


    public static Task<QuerySnapshot> getAllRecipesTask() {
        return db.collection(RECIPE_COLLECTION).get();
    }

    public static Recipe getRecipe(String id) {
        for (int i = 0; i < ALL_RECIPIES.size(); i++) {
            if (ALL_RECIPIES.get(i).getId().equals(id)) {
                return ALL_RECIPIES.get(i);
            }
        }
        return null;
    }

        public static void deleteRecipe (Recipe recipe){
            ALL_RECIPIES.remove(recipe);
            db.collection("RecipeBook").document(recipe.getId()).delete();
        }


        public static void updateRecipe (Recipe recipe){
            db.collection("RecipeBook").document(recipe.getId()).set(recipe);
        }

    }

