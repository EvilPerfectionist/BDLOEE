package com.example.buttonsarray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class SecondActivity extends AppCompatActivity {

    private Cuisine[] cuisines = {
            new Cuisine("鱼香肉丝", R.drawable.yuxiangrousi, Arrays.asList("Carrot", "Paprika", "Pork"), "Medium", "Chinese"),
            new Cuisine("番茄炒蛋", R.drawable.fanqiechaodan, Arrays.asList("Tomato", "Eggs", "Pork"), "Short", "Chinese"),
            new Cuisine("宫爆鸡丁", R.drawable.gongbaojiding, Arrays.asList("Carrot", "Cucumber", "Chicken"), "Medium", "Chinese"),
            new Cuisine("木须肉", R.drawable.muxurou, Arrays.asList("Carrot", "Cucumber", "Pork", "Eggs"), "Short", "Chinese"),
            new Cuisine("肉末茄子", R.drawable.roumoqiezi, Arrays.asList("Eggplant", "Paprika", "Pork"), "Long", "Chinese"),
            new Cuisine("手撕包菜", R.drawable.sousibaocai, Arrays.asList("Cabbage"), "Short", "Chinese"),
            new Cuisine("土豆牛腩", R.drawable.tudouniunan, Arrays.asList("Potato", "Beef"), "Long", "Chinese"),
            new Cuisine("土豆片", R.drawable.tudoupian, Arrays.asList("Potato"), "Short", "Chinese"),
            new Cuisine("炒三丁", R.drawable.chaosanding, Arrays.asList("Carrot", "Cucumber", "Maize"), "Short", "Chinese"),
            new Cuisine("地三鲜", R.drawable.disanxian, Arrays.asList("Eggplant", "Paprika", "Potato"), "Long", "Chinese"),
            new Cuisine("Chili con carne with tomatoes", R.drawable.chili_con_carne_med_tomater, Arrays.asList("Onion", "Beef", "Beans", "Tomato"), "Short", "Swedish"),
            new Cuisine("Egg noodles with chicken club", R.drawable.egg_noodles_with_chicken_club, Arrays.asList("Chicken", "Black Pepper", "Soy Sauce", "Ginger", "Garlic", "Broccoli"), "Medium", "Swedish"),
            new Cuisine("Beef stroganoff with salt cucumber", R.drawable.beef_stroganoff_with_salt_cucumber, Arrays.asList("Beef", "Black Pepper", "Onion", "Tomato", "Salt Cucumber"), "Short", "Swedish"),
            new Cuisine("Roasted winter vegetables with carrot cream and crispy potato", R.drawable.roasted_winter_vegetables_with_carrot_cream_and_crispy_potato, Arrays.asList("Cabbage", "Onion", "Carrot", "Potato", "Cardamom", "Cumin", "Cayenne Pepper", "Spinach", "Sausage"), "Long", "Swedish"),
            new Cuisine("Mushroom steaks with feta cheese and fresh beets", R.drawable.mushroom_steaks_with_feta_cheese_and_fresh_beets, Arrays.asList("Beet", "Arugula", "Mushroom", "Onion", "Garlic", "Thyme", "Black Pepper", "Eggs", "Cheese"), "Medium", "Swedish"),
            new Cuisine("Carrot soup with coconut milk and ginger", R.drawable.carrot_soup_with_coconut_milk_and_ginger, Arrays.asList("Carrot", "Onion", "Ginger", "Coconut Milk"), "Short", "Swedish"),
            new Cuisine("Fried salmon with dill sauce", R.drawable.fried_salmon_with_dill_sauce, Arrays.asList("Potato", "Dill", "Mustard", "Salmon", "Broccoli"), "Medium", "Swedish"),
            new Cuisine("Broccoli soup with coconut milk and crispy halloumi", R.drawable.broccoli_soup_with_coconut_milk_and_crispy_halloumi, Arrays.asList("Onion", "Garlic", "Chili", "Coconut Milk", "Broccoli", "Spinach", "Vinegar"), "Short", "Swedish"),
            new Cuisine("Cheese grilled cabbage and tomato salad", R.drawable.cheese_grilled_cabbage_and_tomato_salad, Arrays.asList("Cabbage", "Chili Sauce", "Ketchup", "Cheese", "Rice", "Tomato", "Salad", "Vinegar", "Mustard", "Pork"), "Short", "Swedish"),
            new Cuisine("Roasted corn soup with quesadillas", R.drawable.roasted_corn_soup_with_quesadillas, Arrays.asList("Garlic", "Corn", "Paprika Powder", "Cumin", "Chili Powder", "Butter", "Cheese" ), "Short", "Swedish"),
            new Cuisine("Meatballs with cream sauce and pasta", R.drawable.meatballs_with_cream_sauce_and_pasta, Arrays.asList("Pasta", "Cabbage", "Eggs", "Beef", "Milk", "Onion", "Black Pepper", "Butter", "Soy Sauce"), "Short", "Swedish"),
            new Cuisine("Pork fillet stew with apple and sage", R.drawable.pork_fillet_stew_with_apple_and_sage, Arrays.asList("Pork", "Apple", "Butter", "Onion", "Leek", "Sage", "Cream", "Soy Sauce", "Black Pepper", "Potato"), "Medium", "Swedish"),
            new Cuisine("Fallow sausage baked with mash", R.drawable.fallow_sausage_baked_with_mash, Arrays.asList("Potato", "Sausage", "Cucumber", "Cheese", "Ketchup", "Milk"), "Medium", "Swedish"),
            new Cuisine("Moose scab with chanterelles and raw-stirred lingonberries", R.drawable.moose_scab_with_chanterelles_and_raw_stirred_lingonberries, Arrays.asList("Mushroom", "Butter", "Black Pepper", "Onion", "Cream", "Soy Sauce", "Berry", "Sugar", "Potato"), "Short", "Swedish"),
            new Cuisine("Musty leafy stew", R.drawable.mustig_lovbiffsgryta, Arrays.asList("Beef", "Butter", "Tomato", "Soy Sauce", "Cream"), "Long", "Swedish")

    };

    private List<Fruit> passed_fruitList = new ArrayList<>();
    private List<Protein> passed_proteinList = new ArrayList<>();
    private List<Spice> passed_spiceList = new ArrayList<>();
    private List<Carbon> passed_carbonList = new ArrayList<>();
    private List<Cuisine> cuisineList = new ArrayList<>();
    private List<String> ingredient_names = new ArrayList<>();

    private CuisineAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        passed_fruitList = (List<Fruit>)intent.getSerializableExtra("fruit_list");
        passed_proteinList = (List<Protein>)intent.getSerializableExtra("protein_list");
        passed_spiceList = (List<Spice>)intent.getSerializableExtra("spice_list");
        passed_carbonList = (List<Carbon>)intent.getSerializableExtra("carbon_list");
        String time = intent.getStringExtra("time");
        String cuisine = intent.getStringExtra("cuisine");
        //Fruit fruit_test = (Fruit) passed_fruitList.get(1);
        for(Fruit single_fruit : passed_fruitList){
            if(single_fruit.getType() == 1){
                ingredient_names.add(single_fruit.getName());
            }
        }
        for(Protein single_protein : passed_proteinList){
            if(single_protein.getType() == 1){
                ingredient_names.add(single_protein.getName());
            }
        }
        for(Spice single_spice : passed_spiceList){
            if(single_spice.getType() == 1){
                ingredient_names.add(single_spice.getName());
            }
        }

        for(Carbon single_carbon : passed_carbonList){
            if(single_carbon.getType() == 1){
                ingredient_names.add(single_carbon.getName());
            }
        }

        /*StringBuilder listString = new StringBuilder();

        for (String s : ingredient_names)
            listString.append(s+" ");

        TextView info = (TextView)findViewById(R.id.text_view_second);
        info.setText(listString);*/

        cuisineList.clear();
        for (int index = 0; index < cuisines.length; index++) {
            if(ingredient_names.containsAll(cuisines[index].getIngredientsList()) && time.equals(cuisines[index].getcooking_time()) && cuisine.equals(cuisines[index].getCuisine_country())){
                cuisineList.add(cuisines[index]);
            }
        }
        //initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_second);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CuisineAdapter(cuisineList);
        recyclerView.setAdapter(adapter);
    }

    private void initFruits() {
        cuisineList.clear();
        for (int index = 0; index < cuisines.length; index++) {
            cuisineList.add(cuisines[index]);
        }
    }
}
