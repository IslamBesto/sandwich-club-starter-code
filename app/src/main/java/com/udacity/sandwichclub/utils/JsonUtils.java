package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        if (json != null) {
            sandwich = new Sandwich();
            try {
                JSONObject sandwichJsonObject = new JSONObject(json);
                JSONObject sandwichNameJsonObject = sandwichJsonObject.getJSONObject("name");
                String sandwichName = sandwichNameJsonObject.getString("mainName");
                JSONArray alsoKnownAs = sandwichNameJsonObject.getJSONArray("alsoKnownAs");
                List<String> alsoKnownAsList = new ArrayList<>();

                for (int i = 0; i < alsoKnownAs.length(); i++) {
                    String alsoKnownAsString = alsoKnownAs.getString(i);
                    alsoKnownAsList.add(alsoKnownAsString);
                }

                String placeOfOrigin = sandwichJsonObject.getString("placeOfOrigin");
                String description = sandwichJsonObject.getString("description");
                String image = sandwichJsonObject.getString("image");

                JSONArray ingredients = sandwichJsonObject.getJSONArray("ingredients");
                List<String> ingredientsList = new ArrayList<>();

                for (int i = 0; i < ingredients.length(); i++) {
                    String ingredientString = ingredients.getString(i);
                    ingredientsList.add(ingredientString);
                }

                sandwich.setAlsoKnownAs(alsoKnownAsList);
                sandwich.setDescription(description);
                sandwich.setImage(image);
                sandwich.setIngredients(ingredientsList);
                sandwich.setMainName(sandwichName);
                sandwich.setPlaceOfOrigin(placeOfOrigin);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return sandwich;
    }
}
