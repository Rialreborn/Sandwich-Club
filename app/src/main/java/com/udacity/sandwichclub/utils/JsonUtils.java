package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    // Set up Constants to be used for retrieving JSON data
    private static String NAME = "name";
    private static String MAIN_NAME = "mainName";
    private static String ALSO_KNOWN_AS = "alsoKnownAs";
    private static String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static String DESCRIPTION = "description";
    private static String IMAGE = "image";
    private static String INGREDIENTS = "ingredients";


    public static Sandwich parseSandwichJson(String json) throws JSONException {
        return makeTastySandwich(json);
    }

    // Create the Sandwich Object
    private static Sandwich makeTastySandwich(String json) throws JSONException {

        // Retrieve the initial JSON Object
        JSONObject jsonObject = new JSONObject(json);
        JSONObject jsonName = jsonObject.getJSONObject(NAME);

        // Retrieve main name
        String mainName = jsonName.getString(MAIN_NAME);

        // Retrieve alsoKnownAs JSON Array and convert into ArrayList
        JSONArray alsoKnownAsJson = jsonName.getJSONArray(ALSO_KNOWN_AS);
        List<String> alsoKnownAsList = new ArrayList<>();
        for (int i = 0; i < alsoKnownAsJson.length(); i++) {
            alsoKnownAsList.add(alsoKnownAsJson.getString(i));
        }

        // Retrieve origin
        String placeOfOrigin = jsonObject.getString(PLACE_OF_ORIGIN);

        // Retrieve description
        String description = jsonObject.getString(DESCRIPTION);

        // Retrieve image URL String
        String image = jsonObject.getString(IMAGE);

        // Retrieve ingredients JSON Array and convert into ArrayList
        JSONArray ingredientsJson = jsonObject.getJSONArray(INGREDIENTS);
        List<String> ingredientsList = new ArrayList<>();
        for (int i = 0; i < ingredientsJson.length(); i++) {
            ingredientsList.add(ingredientsJson.getString(i));
        }

        return new Sandwich(
                mainName,
                alsoKnownAsList,
                placeOfOrigin,
                description,
                image,
                ingredientsList);
    }
}
