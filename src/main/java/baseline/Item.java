/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 John Ashley
 */


package baseline;

import java.util.ArrayList;
import java.util.List;

public class Item {
    //items within an inventory should include a name, serial, and value
    String name;
    String serialNumber;
    int value;
    public static final List<Item> Inventory = new ArrayList<>();


    //sets new item
    public Item() {

        //use this. statements to set them
        this.name = "";
        this.serialNumber = "";
        this.value = 0;

    }

//creates an item if all the values are passed in
    public Item(String name, String serial, int moneyValue) {

        //use this. statements to set them
        this.name = name;
        this.serialNumber = serial;
        this.value = moneyValue;

    }


    //gets and returns name
    public String getName() {
        return name;
    }

    //gets description
    public void setName(String name) {
        this.name = name;
    }


    //gets due date
    public String getSerialNumber() {
        return serialNumber;
    }

    //sets the serial number to what is passed
    public void setSerialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
    }

    //gets value
    public int getValue() {
        return value;
    }

    //sets value
    public void setValue(int moneyValue) {
        this.value = moneyValue;
    }

    //gets the inventory of items
    public static List<Item> getInventory()
    {
        return Inventory;
    }




}