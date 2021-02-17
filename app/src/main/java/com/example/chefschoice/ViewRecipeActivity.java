package com.example.chefschoice;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import com.example.chefschoice.database.FoodCategory;

public class ViewRecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_recipes_activity);

    }
    public void onViewDinnerRecipeClicked(View view){
        Intent intent = new Intent(this, RecipesListActivity.class);
        intent.putExtra(Tags.TAG_FOOD_CATEGORY, FoodCategory.DINNER.ordinal());
        startActivity(intent);
    }

    public void onViewLunchRecipesClicked(View view) {
        Intent intent = new Intent(this, RecipesListActivity.class);
        intent.putExtra(Tags.TAG_FOOD_CATEGORY, FoodCategory.LUNCH.ordinal());
        startActivity(intent);
    }

    public void onViewBreakfastRecipesClicked(View view) {
        Intent intent = new Intent(this, RecipesListActivity.class);
        intent.putExtra(Tags.TAG_FOOD_CATEGORY, FoodCategory.BREAKFAST.ordinal());
        startActivity(intent);
    }

    public void onViewDessertRecipesClicked(View view) {
        Intent intent = new Intent(this, RecipesListActivity.class);
        intent.putExtra(Tags.TAG_FOOD_CATEGORY, FoodCategory.DESSERT.ordinal());
        startActivity(intent);
    }

    public void onViewDrinkRecipesClicked(View view) {
        Intent intent = new Intent(this, RecipesListActivity.class);
        intent.putExtra(Tags.TAG_FOOD_CATEGORY, FoodCategory.DRINKS.ordinal());
        startActivity(intent);
    }

    public void onViewSideDishRecipesClicked(View view) {
        Intent intent = new Intent(this, RecipesListActivity.class);
        intent.putExtra(Tags.TAG_FOOD_CATEGORY, FoodCategory.SIDEDISHES.ordinal());
        startActivity(intent);
    }
}