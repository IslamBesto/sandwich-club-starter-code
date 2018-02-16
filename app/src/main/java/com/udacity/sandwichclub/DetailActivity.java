package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.ui.IngredientTv;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private TextView mPlaceOfOrigin;
    private TextView mMainName;
    private TextView mAlsoKnownAs;
    private TextView mDescription;
    private IngredientTv mIngredients;
    private ImageView mIngredientsIv;
    private LinearLayout mContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        initUI();
        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(mIngredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void initUI() {
        mPlaceOfOrigin = findViewById(R.id.origin_tv);
        mAlsoKnownAs = findViewById(R.id.also_known_tv);
        mDescription = findViewById(R.id.description_tv);
        mIngredientsIv = findViewById(R.id.image_iv);
        mMainName = findViewById(R.id.name_tv);
        mContainer = findViewById(R.id.container);
    }

    private void populateUI(Sandwich sandwich) {
        mPlaceOfOrigin.setText(sandwich.getPlaceOfOrigin());

        List<String> alsoKnownAs = sandwich.getAlsoKnownAs();
        String alsoKnownAsString = "";
        for (int i = 0; i < alsoKnownAs.size(); i++) {
            alsoKnownAsString += alsoKnownAs.get(i) + ", ";
        }
        if (alsoKnownAsString != null && !alsoKnownAsString.equals("")) {

            alsoKnownAsString.substring(0, alsoKnownAsString.length() - 1);
        }
        mAlsoKnownAs.setText(alsoKnownAsString);

        mDescription.setText(sandwich.getDescription());
        mMainName.setText(sandwich.getMainName());

        List<String> ingredients = sandwich.getIngredients();
        for (int i = 0; i < ingredients.size(); i++) {
            IngredientTv ingredientTv = new IngredientTv(this);
            ingredientTv.setIngredientTv(ingredients.get(i));
            mContainer.addView(ingredientTv);
        }
    }
}
