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

* Parameter1: The name of the cuisine. The type is String. Example: `"Cuisine Name"`
* Parameter2: Where to locate the picture of the cuisine. The format is `R.drawable.your_file_name`. There are two steps:
  1. Manually add the image in the folder called drawable-xxhdpi. The path is app\src\main\res\drawable-xxhdpi
  2. Copy the image name and replace the `your_file_name` of the code `R.drawable.your_file_name` with your image name. Example: `R.drawable.chicken_and_rice`
* Parameter3: The ingredients used in this cuisine. The format is `Arrays.asList("first ingredient", "second ingredient", ...)`. Each ingredient name is the type of String. Example: `Arrays.asList("Tomato", "Carrot", "Potato")`
  * ***Attention***: The first letter of the ingredient must be written in capital. In other words, "Tomato" is correct, while "tomato" is false. (The reason of this is that I will explain later)
* Parameter4: The time to prepare for the cuisine. The type is String.
  * ***Attention***: Currently there are only three options: `"Long", "Medium", "Short"`, you must choose one of them to fill in this parameter. (The reason I do this is that, in my opinion, the customers would not care the exact time to prepare for the meal but pay attention to whether they need to spend a lot of time on it.)
* Parameter5: Which country is this cuisine is originated from. The type is string. The options definitely include `"Swedish", "Indian", "Chinese", "Italian"`.
