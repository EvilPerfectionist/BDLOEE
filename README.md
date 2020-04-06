# BDLOEE

## The instructions about how to embed new cuisines into the app

There are two activities in the app project. In other words, the app can show two different pages.

The java code responsible for showing the first activity is written in ***Mainactivity.java***. Similarly, the code for showing the second page can be found in ***SecondActivity.java***. The two files are located in the path: app\\src\\main\\java\\com\\example\\buttonsarray

At the beginning of the ***SecondActivity.java*** file there is a cuisine list, which controls all the possible cuisines we can show in our app. You should see something similar like this:

```java
private Cuisine[] cuisines = {
  ...
  new Cuisine("Moose scab with chanterelles and raw-stirred lingonberries", R.drawable.moose_scab_with_chanterelles_and_raw_stirred_lingonberries, Arrays.asList("Mushroom", "Butter", "Black Pepper", "Onion", "Cream", "Soy Sauce", "Berry", "Sugar", "Potato"), "Short", "Swedish"),
  new Cuisine("Musty leafy stew", R.drawable.mustig_lovbiffsgryta, Arrays.asList("Beef", "Butter", "Tomato", "Soy Sauce", "Cream"), "Long", "Swedish")
};
```
To add a new cuisine to the app, we just simply extend the list. In other words,
you need to add another line of code at the end of this list. The example would be:

```java
new Cuisine(parameter1, parameter2, parameter3, parameter4, parameter5)
```

Apparently, you need to initialize five parameters.

* Parameter1: The name of the cuisine. The type is String. Example: `"Chicken and Rice"`
* Parameter2: Where to locate the picture of the cuisine. The format is `R.drawable.your_file_name`. There are two steps:
  1. Manually add the image in the folder called drawable-xxhdpi. The path is app\src\main\res\drawable-xxhdpi
  2. Copy the image name and replace `your_file_name` in the code `R.drawable.your_file_name` with your image name. Example: `R.drawable.chicken_and_rice`
* Parameter3: The ingredients used in this cuisine. The format is `Arrays.asList("first ingredient", "second ingredient", ...)`. Each ingredient name has the type of String. Example: `Arrays.asList("Tomato", "Carrot", "Potato")`
  * ***Attention***: The first letter of the ingredient must be written in capital. In other words, "Tomato" is correct, while "tomato" is false. (The reason of this I will explain later)
* Parameter4: The time to prepare for the cuisine. The type is String.
  * ***Attention***: Currently there are only three options: `"Long", "Medium", "Short"`, you must choose one of them to fill in this parameter. (The reason I do this is that, in my opinion, the customers would not care the exact time to prepare for the meal but pay attention to whether they need to spend a lot of time on it.)
* Parameter5: Which country this cuisine is originated from. The type is String. The options definitely include `"Swedish", "Indian", "Chinese", "Italian"`.

There are still something to do after you expanding the list.

However, before doing these things, it is important to understand the current method to match the ingredients with a certain cuisine.

------------------------------------------------------------------------------------------------------------------------------------------------------

At the end of the file ***Mainactivity.java***, you can see four private functions, `initFruits(), initProteins(), initSpices(), initCarbon()`. Each element inside these functions represent one button shown on the first page of the app.

For example, if I add two lines of code below, you can see a button called "Beef" shown on the first page.

```java
Protein beef = new Protein("Beef", Protein.TYPE_UNPRESSED);
proteinList.add(beef);
```

The first line of code creates a new Protein instance with two parameters. The first parameter initializes the name of this instance, the second parameter initializes the initial type of this instance. There are two types available. If you choose `TYPE_UNPRESSED`, then the matched button will shown as unselected on the screen. On the contrary, if you choose `TYPE_PRESSED`, then the matched button will shown as selected on the screen.

The second line of code then adds this instance to a list.

After iterating these two steps many times, we will finally get four lists which contain all the vegetable and fruit instances, all the protein instances, all the spice instances, all the carbon instances. Then I transfer these instances to the ***SecondActivity.java*** when we load the second page.

In ***SecondActivity.java***, I create a List called `ingredient_names`.

```java
private List<String> ingredient_names = new ArrayList<>();
```

Then I extract the name parameters from the four lists of instances and store them into this `ingredient_names` List. Of course, before we add the name of a instance to the `ingredient_names` List, we use function `getType()` to check whether the ingredient belongs to the type of `TYPE_UNPRESSED` or `TYPE_PRESSED`. As long as the type of a instance belongs to `TYPE_PRESSED`, we add the name of this instance into the `ingredient_names` List because this means that the customer has this ingredient available.

```java
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
```

As `ingredient_names` List now contains all the names of ingredients that we have, we can check whether the names of ingredients of a new added cuisine are included in this `ingredient_names` List.

```java
for (int index = 0; index < cuisines.length; index++) {
    if(ingredient_names.containsAll(cuisines[index].getIngredientsList()) && time.equals(cuisines[index].getcooking_time()) && cuisine.equals(cuisines[index].getCuisine_country())){
        cuisineList.add(cuisines[index]);
    }
}
```

This for loop will go through all the cuisines stored in the `cuisines` List.

For each specific cuisine, take cuisines[index] as an example, we can use its `getIngredientsList()` method to return all the names of its ingredients. Then we can use `ingredient_names.containsAll()` function to check whether the total ingredients list contains all the necessary ingredients needed by this certain cuisine. Also, we use `getcooking_time()` method to return its cooking time range and `getCuisine_country()` method to return its country type, such as "Swedish", "Chinese", "Indian" and so on. If the time and country of this specific cuisine match the selections of the user in the first page as well as the user has all the ingredients needed for this cuisine, then this cuisine will be added to the `cuisineList`, as shown in the last line of the code above. This `cuisineList` contains the final cuisines shown on the screen.

In conclusion, we create four list of ingredients(instances) in ***Mainactivity.java***. These instances will be shown as buttons on the first page. These instances are initialized with the type of `TYPE_UNPRESSED` so that the buttons are initially shown as grey and unselected. When users press some of the buttons, theses buttons will become green and the type of these buttons will become `TYPE_PRESSED`, which means users think these ingredients are available. When users make their choices about what kinds of ingredients they have, the cooking time and the cuisine category("Swedish", "Indian", ...) in the first page and click the button to activate the second page, the names of `TYPE_PRESSED` instances will be stored in the `ingredient_names` List and the cooking time and the cuisine category information are also recorded. Finally, the cuisines whose names of ingredients are a subset of `ingredient_names` and whose cooking time and the cuisine category information match the transferred information will show up.

------------------------------------------------------------------------------------------------------------------------------------------------------

Now you should understand the method that matches the ingredients with a certain cuisine. Therefore, after you expand the cuisine list with a new cuisine with the code:

```java
new Cuisine(parameter1, parameter2, parameter3, parameter4, parameter5)
```

* You should check whether in parameter 3 the first letter of the ingredients are written in capital because all the first letter of the names of the available ingredients I initialized in `initFruits(), initProteins(), initSpices(), initCarbon()` are written in capital. In other words, `ingredient_names.containsAll()` function will return false when all the ingredient names stored in `ingredient_names` List are in capitals but all the ingredient names initialized in the new cuisine are in lower case.
* If the new added cuisine contains ingredients which the four lists `initFruits(), initProteins(), initSpices(), initCarbon()` don't have, you should add these ingredients into these lists.
