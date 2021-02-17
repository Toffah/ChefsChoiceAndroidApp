package com.example.chefschoice;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.chefschoice.database.Recipe;
import com.example.chefschoice.database.RecipeRepository;


public class RecipeSingleActivity extends AppCompatActivity {

    private Toolbar mTopToolbar;
    private Recipe singleRecipe;
    private RecipeSingleActivity thisActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_single_recipe_activity);

        thisActivity = this;

       String id = getIntent().getStringExtra(Tags.TAG_RECIPE_ID);

        singleRecipe = RecipeRepository.getRecipe(id);

        TextView recipeName = findViewById(R.id.recipeName);
        TextView recipeName2 = findViewById(R.id.recipeName2);
        TextView recipeIngredients = findViewById(R.id.recipeIngredients);
        TextView recipeInstructions = findViewById(R.id.recipeInstructions);

        recipeName.setText(singleRecipe.getName());
        recipeName2.setText(singleRecipe.getName());
        recipeIngredients.setText(singleRecipe.getIngredients());
        recipeInstructions.setText(singleRecipe.getInstructions());

        mTopToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.single_recipe_menu, menu);
        return true;
    }

    public void editRecipe(MenuItem item) {
        Intent intent = new Intent(this, EditRecipeActivity.class);
        intent.putExtra(Tags.TAG_RECIPE_ID, singleRecipe.getId());
        startActivity(intent);
    }

    public void deleteRecipe(MenuItem item) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Delete Recipe");
        builder.setMessage("Are you sure you want to delete the recipe?");
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RecipeRepository.deleteRecipe(singleRecipe);
                Toast.makeText(thisActivity, "Recipe successfully deleted", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(thisActivity, MainActivity.class);
                startActivity(intent);
                            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }
}
