package nl.saxion.ap.drink.model;

/**
 * Created by Roy on 9-3-2017.
 */

public class Card {

    private int resource;
    private String name;
    private int value;
    private int type; //harten=0, klaver=1, ruiten=2, schoppen=3

    public Card(String name, int value, int type, int resource) {
        this.resource = resource;
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getResource() {
        return resource;
    }

    public int getValue() {
        return value;
    }

    public int getType() {
        return type;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setValue(int value) {
//        this.value = value;
//    }
//
//    public void setType(int type) {
//        this.type = type;
//    }
//
//    public void setResource(int resource) {
//        this.resource = resource;
//    }
}
