# BDLOEE

## The instructions about how to embed new cuisines into the app

There are two activities in the app project. In other words, the app can show two different pages.

The java code responsible for showing the first activity is written in ***Mainactivity.java***. Similarly, the code for showing the second page can be found in ***SecondActivity.java***. The two files are located in the path: app\\src\\main\\java\\com\\example\\buttonsarray

At the beginning of the ***SecondActivity.java*** file there is a cuisine list, which controls all the possible cuisines we can show in our app.

  private Cuisine[] cuisines = {
    ...
    new Cuisine("Moose scab with chanterelles and raw-stirred lingonberries", R.drawable.moose_scab_with_chanterelles_and_raw_stirred_lingonberries, Arrays.asList("Mushroom", "Butter", "Black Pepper", "Onion", "Cream", "Soy Sauce", "Berry", "Sugar", "Potato"), "Short", "Swedish"),
    new Cuisine("Musty leafy stew", R.drawable.mustig_lovbiffsgryta, Arrays.asList("Beef", "Butter", "Tomato", "Soy Sauce", "Cream"), "Long", "Swedish")
  };
