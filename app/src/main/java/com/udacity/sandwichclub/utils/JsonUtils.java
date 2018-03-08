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
    private static String JSON_FAIL = "N/A";


    public static Sandwich parseSandwichJson(String json) throws JSONException {
        return makeTastySandwich(json);
    }

    // Create the Sandwich Object
    private static Sandwich makeTastySandwich(String json) throws JSONException {

        // Retrieve the initial JSON Object
        JSONObject jsonObject = new JSONObject(json);

        // Retrieve main name
        String mainName = jsonObject.getJSONObject(NAME).optString(MAIN_NAME, JSON_FAIL);

        // Retrieve alsoKnownAs JSON Array and convert into ArrayList
        JSONArray alsoKnownAsJson = jsonObject.getJSONObject(NAME).getJSONArray(ALSO_KNOWN_AS);
        List<String> alsoKnownAsList = createList(alsoKnownAsJson);

        // Retrieve origin
        String placeOfOrigin = jsonObject.optString(PLACE_OF_ORIGIN, JSON_FAIL);

        // Retrieve description
        String description = jsonObject.optString(DESCRIPTION, JSON_FAIL);

        // Retrieve image URL String
        String image = jsonObject.optString(IMAGE, JSON_FAIL);

        // Retrieve ingredients JSON Array and convert into ArrayList
        JSONArray ingredientsJson = jsonObject.getJSONArray(INGREDIENTS);
        List<String> ingredientsList = createList(ingredientsJson);

        return new Sandwich(
                mainName,
                alsoKnownAsList,
                placeOfOrigin,
                description,
                image,
                ingredientsList);
    }

    private static List<String> createList(JSONArray jsonArray) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            stringList.add(jsonArray.optString(i, JSON_FAIL));
        }
        return stringList;
    }
}
