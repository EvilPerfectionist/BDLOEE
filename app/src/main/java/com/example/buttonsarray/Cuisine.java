package com.example.buttonsarray;

import java.util.List;

public class Cuisine {

    private String name;
    private int imageId;
    private List<String> ingredientsList;
    private String cooking_time;
    private String cuisine_country;

    public Cuisine(String name, int imageId, List<String> ingredientsList, String cooking_time, String cuisine_country) {
        this.name = name;
        this.imageId = imageId;
        this.ingredientsList = ingredientsList;
        this.cooking_time =  cooking_time;
        this.cuisine_country = cuisine_country;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public List<String> getIngredientsList() { return ingredientsList;}

    public String getcooking_time() {
        return cooking_time;
    }

    public String getCuisine_country(){
        return cuisine_country;
    }
}
