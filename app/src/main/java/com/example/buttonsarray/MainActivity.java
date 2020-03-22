package com.example.buttonsarray;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();
    private List<Protein> proteinList = new ArrayList<>();
    private List<Spice> spiceList = new ArrayList<>();
    private List<Carbon> carbonList = new ArrayList<>();
    private Spinner cuisine_spinner;
    private String cuisineValue = "Italian";
    private Spinner time_spinner;
    private String timeValue = "Short";

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        initFruits(); // 初始化水果数据
        initProteins();
        initSpices();
        initCarbon();

        LoadPreferences();

        /*for(Fruit single_fruit : fruitList){
            Log.d(TAG, single_fruit.getName() + String.valueOf(single_fruit.getType()));
        }*/

        int mNoOfColumns = Utility.calculateNoOfColumns(getApplicationContext(), 110);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager gridlayoutManager = new GridLayoutManager(this, mNoOfColumns);
        recyclerView.setLayoutManager(gridlayoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        RecyclerView recyclerView_protein = (RecyclerView) findViewById(R.id.recycler_view_protein);
        recyclerView_protein.setOverScrollMode(View.OVER_SCROLL_NEVER);
        GridLayoutManager gridlayoutManager_protein = new GridLayoutManager(this, mNoOfColumns);
        recyclerView_protein.setLayoutManager(gridlayoutManager_protein);
        ProteinAdapter adapter_protein = new ProteinAdapter(proteinList);
        recyclerView_protein.setAdapter(adapter_protein);

        RecyclerView recyclerView_spice = (RecyclerView) findViewById(R.id.recycler_view_spice);
        GridLayoutManager gridlayoutManager_spice = new GridLayoutManager(this, mNoOfColumns);
        recyclerView_spice.setLayoutManager(gridlayoutManager_spice);
        SpiceAdapter adapter_spice = new SpiceAdapter(spiceList);
        recyclerView_spice.setAdapter(adapter_spice);

        RecyclerView recyclerView_carbon = (RecyclerView) findViewById(R.id.recycler_view_carbon);
        GridLayoutManager gridlayoutManager_carbon = new GridLayoutManager(this, mNoOfColumns);
        recyclerView_carbon.setLayoutManager(gridlayoutManager_carbon);
        CarbonAdapter adapter_carbon = new CarbonAdapter(carbonList);
        recyclerView_carbon.setAdapter(adapter_carbon);

        cuisine_spinner = (Spinner) findViewById(R.id.cuisine_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> cuisine_adapter = ArrayAdapter.createFromResource(this,
                R.array.cuisines_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        cuisine_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        // Apply the adapter to the spinner
        cuisine_spinner.setAdapter(cuisine_adapter);
        if (cuisineValue != null) {
            int cuisine_spinnerPosition = cuisine_adapter.getPosition(cuisineValue);
            cuisine_spinner.setSelection(cuisine_spinnerPosition);
        }

        time_spinner = (Spinner) findViewById(R.id.time_spinner);
        ArrayAdapter<CharSequence> time_adapter = ArrayAdapter.createFromResource(this,
                R.array.times_array, R.layout.spinner_item);
        time_adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        time_spinner.setAdapter(time_adapter);
        if (timeValue != null) {
            int time_spinnerPosition = time_adapter.getPosition(timeValue);
            time_spinner.setSelection(time_spinnerPosition);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("fruit_list", (Serializable)fruitList);
                intent.putExtra("protein_list", (Serializable)proteinList);
                intent.putExtra("spice_list", (Serializable)spiceList);
                intent.putExtra("carbon_list", (Serializable)carbonList);
                intent.putExtra("cuisine", cuisine_spinner.getSelectedItem().toString());
                intent.putExtra("time", time_spinner.getSelectedItem().toString());
                startActivity(intent);
                //Fruit fruit_test = (Fruit) fruitList.get(0);
                //Toast.makeText(MainActivity.this, fruit_test.getName(), Toast.LENGTH_SHORT).show();
                }
        });
    }

    private void SavePreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for(Fruit single_fruit : fruitList){
            editor.putInt(single_fruit.getName(), single_fruit.getType());
            //Log.d(TAG, single_fruit.getName() + " save " + String.valueOf(single_fruit.getType()));
        }
        for(Protein single_protein : proteinList){
            editor.putInt(single_protein.getName(), single_protein.getType());
        }
        for(Spice single_spice : spiceList){
            editor.putInt(single_spice.getName(), single_spice.getType());
        }
        for(Carbon single_carbon : carbonList){
            editor.putInt(single_carbon.getName(), single_carbon.getType());
        }
        editor.putString("cuisine_value" ,cuisine_spinner.getSelectedItem().toString());
        editor.putString("time_value" ,time_spinner.getSelectedItem().toString());
        editor.commit();   // I missed to save the data to preference here,.
    }

    private void LoadPreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        for(Fruit single_fruit : fruitList){
            if(sharedPreferences.getInt(single_fruit.getName(), 0) == 1){
                //Log.d(TAG, single_fruit.getName() + " load ");
                single_fruit.setType_pressed();
            }
        }
        for(Protein single_protein : proteinList){
            if(sharedPreferences.getInt(single_protein.getName(), 0) == 1){
                single_protein.setType_pressed();
            }
        }
        for(Spice single_spice : spiceList){
            if(sharedPreferences.getInt(single_spice.getName(), 0) == 1){
                single_spice.setType_pressed();
            }
        }
        for(Carbon single_carbon : carbonList){
            if(sharedPreferences.getInt(single_carbon.getName(), 0) == 1){
                single_carbon.setType_pressed();
            }
        }
        cuisineValue = sharedPreferences.getString("cuisine_value", "Italian");
        timeValue = sharedPreferences.getString("time_value", "Short");
    }

    @Override
    public void onBackPressed(){
        //SavePreferences();
        Log.d(TAG, "onBackPressed");
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SavePreferences();
        Log.d(TAG, "onStop");
    }

    private void initFruits() {for (int i = 0; i < 1; i++) {
        Fruit onion = new Fruit("Onion", Fruit.TYPE_UNPRESSED);
        fruitList.add(onion);
        Fruit potato = new Fruit("Potato", Fruit.TYPE_UNPRESSED);
        fruitList.add(potato);
        Fruit tomato = new Fruit("Tomato", Fruit.TYPE_UNPRESSED);
        fruitList.add(tomato);
        Fruit carrot = new Fruit("Carrot", Fruit.TYPE_UNPRESSED);
        fruitList.add(carrot);
        Fruit zucchini = new Fruit("Zucchini", Fruit.TYPE_UNPRESSED);
        fruitList.add(zucchini);
        Fruit cucumber = new Fruit("Cucumber", Fruit.TYPE_UNPRESSED);
        fruitList.add(cucumber);
        Fruit maize = new Fruit("Maize", Fruit.TYPE_UNPRESSED);
        fruitList.add(maize);
        Fruit paprika = new Fruit("Paprika", Fruit.TYPE_UNPRESSED);
        fruitList.add(paprika);
        Fruit eggplant = new Fruit("Eggplant", Fruit.TYPE_UNPRESSED);
        fruitList.add(eggplant);
        Fruit cabbage = new Fruit("Cabbage", Fruit.TYPE_UNPRESSED);
        fruitList.add(cabbage);
        Fruit spinach = new Fruit("Spinach", Fruit.TYPE_UNPRESSED);
        fruitList.add(spinach);
        Fruit salt_cucumber = new Fruit("Salt Cucumber", Fruit.TYPE_UNPRESSED);
        fruitList.add(salt_cucumber);
        Fruit broccoli = new Fruit("Broccoli", Fruit.TYPE_UNPRESSED);
        fruitList.add(broccoli);
        Fruit beet = new Fruit("Beet", Fruit.TYPE_UNPRESSED);
        fruitList.add(beet);
        Fruit thyme = new Fruit("Thyme", Fruit.TYPE_UNPRESSED);
        fruitList.add(thyme);
        Fruit arugula = new Fruit("Arugula", Fruit.TYPE_UNPRESSED);
        fruitList.add(arugula);
        Fruit mushroom = new Fruit("Mushroom", Fruit.TYPE_UNPRESSED);
        fruitList.add(mushroom);
        Fruit dill = new Fruit("Dill", Fruit.TYPE_UNPRESSED);
        fruitList.add(dill);
        Fruit apple = new Fruit("Apple", Fruit.TYPE_UNPRESSED);
        fruitList.add(apple);
        Fruit chili = new Fruit("Chili", Fruit.TYPE_UNPRESSED);
        fruitList.add(chili);
        Fruit salad = new Fruit("Salad", Fruit.TYPE_UNPRESSED);
        fruitList.add(salad);
        Fruit corn = new Fruit("Corn", Fruit.TYPE_UNPRESSED);
        fruitList.add(corn);
        Fruit leek = new Fruit("Leek", Fruit.TYPE_UNPRESSED);
        fruitList.add(leek);
        Fruit sage = new Fruit("Sage", Fruit.TYPE_UNPRESSED);
        fruitList.add(sage);
        Fruit berry = new Fruit("Berry", Fruit.TYPE_UNPRESSED);
        fruitList.add(berry);

        Collections.sort(fruitList, new Comparator() {
            @Override
            public int compare(Object fruitOne, Object fruitTwo) {
                //use instanceof to verify the references are indeed of the type in question
                return ((Fruit)fruitOne).getName().compareTo(((Fruit)fruitTwo).getName());
            }
        });
    }
    }

    private void initProteins() {for (int i = 0; i < 1; i++) {
        Protein beef = new Protein("Beef", Protein.TYPE_UNPRESSED);
        proteinList.add(beef);
        Protein pork = new Protein("Pork", Protein.TYPE_UNPRESSED);
        proteinList.add(pork);
        Protein chicken = new Protein("Chicken", Protein.TYPE_UNPRESSED);
        proteinList.add(chicken);
        Protein fish = new Protein("Fish", Protein.TYPE_UNPRESSED);
        proteinList.add(fish);
        Protein beans = new Protein("Beans", Protein.TYPE_UNPRESSED);
        proteinList.add(beans);
        Protein eggs = new Protein("Eggs", Protein.TYPE_UNPRESSED);
        proteinList.add(eggs);
        Protein milk = new Protein("Milk", Protein.TYPE_UNPRESSED);
        proteinList.add(milk);
        //Protein yoghurt = new Protein("Yoghurt", Protein.TYPE_UNPRESSED);
        //proteinList.add(yoghurt);
        Protein cheese = new Protein("Cheese", Protein.TYPE_UNPRESSED);
        proteinList.add(cheese);
        Protein tofu = new Protein("Tofu", Protein.TYPE_UNPRESSED);
        proteinList.add(tofu);
        Protein sausage = new Protein("Sausage", Protein.TYPE_UNPRESSED);
        proteinList.add(sausage);
        Protein salmon = new Protein("Salmon", Protein.TYPE_UNPRESSED);
        proteinList.add(salmon);

        Collections.sort(proteinList, new Comparator() {
            @Override
            public int compare(Object proteinOne, Object proteinTwo) {
                //use instanceof to verify the references are indeed of the type in question
                return ((Protein)proteinOne).getName().compareTo(((Protein)proteinTwo).getName());
            }
        });
    }
    }

    private void initSpices() {for (int i = 0; i < 1; i++) {
        Spice chili = new Spice("Chili", Spice.TYPE_UNPRESSED);
        spiceList.add(chili);
        Spice oregano = new Spice("Oregano", Spice.TYPE_UNPRESSED);
        spiceList.add(oregano);
        Spice ginger = new Spice("Ginger", Spice.TYPE_UNPRESSED);
        spiceList.add(ginger);
        Spice black_pepper = new Spice("Black Pepper", Spice.TYPE_UNPRESSED);
        spiceList.add(black_pepper);
        Spice parsley = new Spice("Parsley", Spice.TYPE_UNPRESSED);
        spiceList.add(parsley);
        Spice basilica = new Spice("Basilica", Spice.TYPE_UNPRESSED);
        spiceList.add(basilica);
        Spice mustard = new Spice("Mustard", Spice.TYPE_UNPRESSED);
        spiceList.add(mustard);
        Spice curry = new Spice("Curry", Spice.TYPE_UNPRESSED);
        spiceList.add(curry);
        Spice rosemary = new Spice("Rosemary", Spice.TYPE_UNPRESSED);
        spiceList.add(rosemary);
        Spice cinnamon = new Spice("Cinnamon", Spice.TYPE_UNPRESSED);
        spiceList.add(cinnamon);
        Spice garlic = new Spice("Garlic", Spice.TYPE_UNPRESSED);
        spiceList.add(garlic);
        Spice soy_sauce = new Spice("Soy Sauce", Spice.TYPE_UNPRESSED);
        spiceList.add(soy_sauce);
        Spice cardamom = new Spice("Cardamom", Spice.TYPE_UNPRESSED);
        spiceList.add(cardamom);
        Spice cumin = new Spice("Cumin", Spice.TYPE_UNPRESSED);
        spiceList.add(cumin);
        Spice cayenne_pepper = new Spice("Cayenne Pepper", Spice.TYPE_UNPRESSED);
        spiceList.add(cayenne_pepper);
        Spice coconut_milk = new Spice("Coconut Milk", Spice.TYPE_UNPRESSED);
        spiceList.add(coconut_milk);
        Spice vinegar = new Spice("Vinegar", Spice.TYPE_UNPRESSED);
        spiceList.add(vinegar);
        Spice chili_sauce = new Spice("Chili Sauce", Spice.TYPE_UNPRESSED);
        spiceList.add(chili_sauce);
        Spice ketchup = new Spice("Ketchup", Spice.TYPE_UNPRESSED);
        spiceList.add(ketchup);
        Spice paprika_powder = new Spice("Paprika Powder", Spice.TYPE_UNPRESSED);
        spiceList.add(paprika_powder);
        Spice chili_powder = new Spice("Chili Powder", Spice.TYPE_UNPRESSED);
        spiceList.add(chili_powder);
        Spice butter = new Spice("Butter", Spice.TYPE_UNPRESSED);
        spiceList.add(butter);
        Spice cream = new Spice("Cream", Spice.TYPE_UNPRESSED);
        spiceList.add(cream);
        Spice sugar = new Spice("Sugar", Spice.TYPE_UNPRESSED);
        spiceList.add(sugar);

        Collections.sort(spiceList, new Comparator() {
            @Override
            public int compare(Object spiceOne, Object spiceTwo) {
                //use instanceof to verify the references are indeed of the type in question
                return ((Spice)spiceOne).getName().compareTo(((Spice)spiceTwo).getName());
            }
        });
    }
    }

    private void initCarbon(){
        for (int i = 0; i < 1; i++) {
            Carbon rice = new Carbon("Rice", Carbon.TYPE_UNPRESSED);
            carbonList.add(rice);
            Carbon pasta = new Carbon("Pasta", Carbon.TYPE_UNPRESSED);
            carbonList.add(pasta);
        }

        Collections.sort(carbonList, new Comparator() {
            @Override
            public int compare(Object carbonOne, Object carbonTwo) {
                //use instanceof to verify the references are indeed of the type in question
                return ((Carbon)carbonOne).getName().compareTo(((Carbon)carbonTwo).getName());
            }
        });
    }
}
