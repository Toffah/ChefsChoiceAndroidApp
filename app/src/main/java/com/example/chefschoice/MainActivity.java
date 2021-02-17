package com.example.chefschoice;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import com.example.chefschoice.database.FoodCategory;
import com.example.chefschoice.database.Recipe;
import com.example.chefschoice.database.RecipeRepository;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.function.Consumer;

import static com.example.chefschoice.database.RecipeRepository.*;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipeRepository.getAllRecipesTask().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                queryDocumentSnapshots.forEach(new Consumer<QueryDocumentSnapshot>() {
                    @Override
                    public void accept(QueryDocumentSnapshot queryDocumentSnapshot) {
                        Recipe recipe = new Recipe(
                                queryDocumentSnapshot.getId(),
                                (String) queryDocumentSnapshot.get(KEY_RECIPENAME),
                                (String) queryDocumentSnapshot.get(KEY_RECIPE_INGREDIENTS),
                                (String) queryDocumentSnapshot.get(KEY_RECIPE_INSTRUCTIONS),
                                queryDocumentSnapshot.get(KEY_RECIPE_FOOD_CATEGORY, FoodCategory.class));

                        ALL_RECIPIES.add(recipe);
                        System.out.println(recipe);
                    }
                });

            }
        });



    }

    public void onAddRecipeClicked(View view) {
        Intent intent = new Intent(this, AddRecipeActivity.class);
        startActivity(intent);
    }

    public void onViewRecipeClicked(View view) {
        Intent intent = new Intent(this, ViewRecipeActivity.class);
        startActivity(intent);

    }


    public void onAddRecipePictureClicked(View view) {
        Intent intent = new Intent(this, AddRecipeActivity.class);
        startActivity(intent);
    }

    public void onViewRecipePictureClicked(View view) {
        Intent intent = new Intent(this, ViewRecipeActivity.class);
        startActivity(intent);
    }

    public void superSecretEasterEggRecipes(View view) {
        Intent intent = new Intent(this, RecipesListActivity.class);
        intent.putExtra(Tags.TAG_FOOD_CATEGORY, FoodCategory.EASTEREGG.ordinal());
        startActivity(intent);
    }

    public void onLogInClicked(View view) {
        Intent intent = new Intent(this, AuthenticationSimpleActivity.class);
        startActivity(intent);
    }

    public void onAllRecipesClicked(View view) {
        Intent intent = new Intent(this, AllRecipesListActivity.class);
        startActivity(intent);
    }
}