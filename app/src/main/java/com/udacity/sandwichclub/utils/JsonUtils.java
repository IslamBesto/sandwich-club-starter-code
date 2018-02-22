package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String KEY_NAME = "name";
    private static final String KEY_MAIN_NAME = "mainName";
    private static final String NO_AVAILABLE_TEXT = "NA";
    private static final String KEY_ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String KEY_PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String KET_DESCRIPTION = "description";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = null;
        if (json != null) {
            sandwich = new Sandwich();
            try {
                JSONObject sandwichJsonObject = new JSONObject(json);
                JSONObject sandwichNameJsonObject = sandwichJsonObject.getJSONObject(KEY_NAME);
                String sandwichName = sandwichNameJsonObject.optString(KEY_MAIN_NAME,
                        NO_AVAILABLE_TEXT);
                JSONArray alsoKnownAs = sandwichNameJsonObject.getJSONArray(KEY_ALSO_KNOWN_AS);
                List<String> alsoKnownAsList = new ArrayList<>();

                for (int i = 0; i < alsoKnownAs.length(); i++) {
                    String alsoKnownAsString = alsoKnownAs.getString(i);
                    alsoKnownAsList.add(alsoKnownAsString);
                }

                String placeOfOrigin = sandwichJsonObject.getString(KEY_PLACE_OF_ORIGIN);
                String description = sandwichJsonObject.getString(KET_DESCRIPTION);
                String image = sandwichJsonObject.getString(KEY_IMAGE);

                JSONArray ingredients = sandwichJsonObject.getJSONArray(KEY_INGREDIENTS);
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
