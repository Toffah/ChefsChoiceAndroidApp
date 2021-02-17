package com.example.chefschoice;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chefschoice.database.FoodCategory;
import com.example.chefschoice.database.Recipe;
import com.example.chefschoice.database.RecipeRepository;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import org.jetbrains.annotations.NotNull;


public class AddRecipeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText editTextRecipeName;
    private EditText editTextRecipeIngredients;
    private EditText editTextRecipeInstructions;
    private FoodCategory foodCategory;
    private Spinner spinner;
    private static final String[] paths = {"Dinner", "Lunch", "Breakfast", "Desserts", "Drinks", "Side Dishes"};

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final String KEY_RECIPENAME = "name";
    private static final String KEY_RECIPE_INGREDIENTS = "ingredients";
    private static final String KEY_RECIPE_INSTRUCTIONS = "instructions";
    private static final String KEY_RECIPE_FOOD_CATEGORY = "foodCategory";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe_activity);

        editTextRecipeName = findViewById(R.id.editTextRecipeName);
        editTextRecipeIngredients = findViewById(R.id.editTextRecipeIngredients);
        editTextRecipeInstructions = findViewById(R.id.editTextRecipeInstructions);

        spinner = (Spinner) findViewById(R.id.spinnerCategory);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddRecipeActivity.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        switch (position) {
            case 0:
                foodCategory = FoodCategory.DINNER;
                break;
            case 1:
                foodCategory = FoodCategory.LUNCH;
                break;
            case 2:
                foodCategory = FoodCategory.BREAKFAST;
                break;
            case 3:
                foodCategory = FoodCategory.DESSERT;
                break;
            case 4:
                foodCategory = FoodCategory.DRINKS;
                break;
            case 5:
                foodCategory = FoodCategory.SIDEDISHES;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void onSaveButtonClicked(View view) {

        String name = editTextRecipeName.getText().toString();
        String ingedients = editTextRecipeIngredients.getText().toString();
        String instructions = editTextRecipeInstructions.getText().toString();


        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setIngredients(ingedients);
        recipe.setInstructions(instructions);
        recipe.setFoodCategory(foodCategory);


        RecipeRepository.addRecipe(recipe)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(AddRecipeActivity.this, "Recipe added successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AddRecipeActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(AddRecipeActivity.this, "Recipe failed to save", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}