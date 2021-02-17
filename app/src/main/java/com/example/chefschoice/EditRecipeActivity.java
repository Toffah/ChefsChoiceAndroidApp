package com.example.chefschoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chefschoice.database.FoodCategory;
import com.example.chefschoice.database.Recipe;
import com.example.chefschoice.database.RecipeRepository;

public class EditRecipeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText editTextRecipeName;
    private EditText editTextRecipeIngredients;
    private EditText editTextRecipeInstructions;
    private FoodCategory foodCategory;
    private Spinner spinner;
    private static final String[] paths = {"Dinner", "Lunch", "Breakfast", "Desserts", "Drinks", "Side Dishes", "Easter Egg"};
    private String recipeId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_recipe_activity);

        editTextRecipeName = findViewById(R.id.editTextRecipeName);
        editTextRecipeIngredients = findViewById(R.id.editTextRecipeIngredients);
        editTextRecipeInstructions = findViewById(R.id.editTextRecipeInstructions);

        spinner = (Spinner) findViewById(R.id.spinnerCategory);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditRecipeActivity.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        recipeId = getIntent().getStringExtra(Tags.TAG_RECIPE_ID);
        Recipe singleRecipe = RecipeRepository.getRecipe(recipeId);

        editTextRecipeName.setText(singleRecipe.getName());
        editTextRecipeIngredients.setText(singleRecipe.getIngredients());
        editTextRecipeInstructions.setText(singleRecipe.getInstructions());

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
            case 6:
                foodCategory = FoodCategory.EASTEREGG;
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void onSaveButtonClicked(View view) {
        RecipeRepository.editRecipe(new Recipe(
                recipeId,
                editTextRecipeName.getText().toString(),
                editTextRecipeIngredients.getText().toString(),
                editTextRecipeInstructions.getText().toString(),
                foodCategory),recipeId);

        Toast.makeText(this, "Recipe edited successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

