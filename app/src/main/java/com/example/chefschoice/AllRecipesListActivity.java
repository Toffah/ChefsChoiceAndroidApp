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

public class AllRecipesListActivity extends AppCompatActivity {

    private ListView recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipes_list_activity);

        com.example.chefschoice.AllRecipesListActivity thisActivity = this;


        List<Recipe> allRecipes = ALL_RECIPIES.stream()
                .collect(Collectors.toList());

        List<String> filteredRecipieNames = allRecipes.stream()
                .map(Recipe::getName)
                .collect(Collectors.toList());

        recipeList = findViewById(R.id.recipeList);

        recipeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recipe clickedRecipe = allRecipes.get(position);

                Intent intent = new Intent(thisActivity, RecipeSingleActivity.class);
                intent.putExtra(Tags.TAG_RECIPE_ID, clickedRecipe.getId());
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

