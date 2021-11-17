package baseline;

import com.google.gson.Gson;

import java.io.*;
import java.util.Formatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileIO {



    public Item openHTMLFile(File file)
    {
        Item item = new Item();
        return item;
    }

    public Item openJSONFile(File file) {
        Item item = new Item();
        return item;
    }


    public Item openTSVFile(File file) {
        Item item = new Item();
        return item;
    }


    public void saveHTMLFile(File file, List<Item> itemList)
    {

    }


    public void saveJSONFile(File file, List<Item> itemList) {
        Gson gson = new Gson();
        //write gson file by converting inventory to gson
        try {
            Writer writer = new FileWriter(file);
            gson.toJson(itemList,writer);
            writer.close();
            //if for some reason it doesnt work, note error
        } catch (IOException e) {

            System.out.println("Error making file");
        }

    }


    public void saveTSVFile(File file, List<Item> itemList) {
        //format txt file
        try (Formatter output = new Formatter(file)) {
            output.format("Serial Number\tItem Name\tItem Value%n");
            //for each item in the list, format/print it to file
            for (Item item : itemList) {
                output.format("%s\t%s\t\t$%d%n", item.getSerialNumber(), item.getName(), item.getValue());
            }
            //if for some reason it doesnt work, note error
        } catch (IOException e) {
            System.out.println("Error making file");
        }
    }



}
