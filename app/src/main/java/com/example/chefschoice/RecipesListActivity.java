package com.example.chefschoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.chefschoice.database.FoodCategory;
import com.example.chefschoice.database.Recipe;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.chefschoice.database.RecipeRepository.ALL_RECIPIES;

public class RecipesListActivity extends AppCompatActivity {

    private ListView recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_list_activity);

        RecipesListActivity thisActivity = this;

        int category = getIntent().getIntExtra(Tags.TAG_FOOD_CATEGORY, FoodCategory.DINNER.ordinal());
        final FoodCategory foodCategory = FoodCategory.values()[category];

        List<Recipe> filteredRecipies =ALL_RECIPIES.stream()
                .filter(r -> r.getFoodCategory() == foodCategory)
                .collect(Collectors.toList());

        List<String> filteredRecipieNames = filteredRecipies.stream()
                .map(Recipe::getName)
                .collect(Collectors.toList());

        recipeList = findViewById(R.id.recipeList);

        recipeList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe clickedRecipe = filteredRecipies.get(position);

                Intent intent = new Intent(thisActivity, RecipeSingleActivity.class);
                intent.putExtra(Tags.TAG_RECIPE_ID,clickedRecipe.getId());
                startActivity(intent);
            }
        });

        ArrayAdapter<String> recipeAdapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                filteredRecipieNames
        );

        recipeList.setAdapter(recipeAdapter);
    }

}