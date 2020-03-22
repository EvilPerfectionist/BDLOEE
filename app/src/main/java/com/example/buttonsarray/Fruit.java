package com.example.buttonsarray;

import java.io.Serializable;

public class Fruit implements Serializable {

    private String name;

    public static final int TYPE_PRESSED = 1;
    public static final int TYPE_UNPRESSED = 0;

    private int type;

    public Fruit(String name, int type){
        this.name = name;
        this.type = type;
    }

    public String getName(){
        return name;
    }
    public int getType() {
        return type;
    }
    public void setType_pressed() {
        this.type = TYPE_PRESSED;
    }
    public void setType_unpressed() {
        this.type = TYPE_UNPRESSED;
    }
}
