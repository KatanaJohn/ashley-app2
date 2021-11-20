/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 John Ashley
 */


package baseline;

import com.google.gson.Gson;

import java.io.*;
import java.util.*;

public class FileIO {



    public void openHTMLFile(File file) {
        //clears inventory
        Item.getInventory().clear();

        //makes strings for values
        String name;
        String value;
        String serial;


        try (Scanner fileInput = new Scanner(file)) {

            while (true) {
                String currentLine = fileInput.nextLine();

                if (currentLine.equals("    <th>Serial Number</th>")) {

                    while (true) {
                        fileInput.nextLine();

                        currentLine = fileInput.nextLine();
                        if (currentLine.equals("</table> ")) {
                            break;
                        }


                        currentLine = fileInput.nextLine();
                        name = currentLine.replace("<td>", "").replace("</td>", "")
                                .replaceAll("^\s*", "");
                        currentLine = fileInput.nextLine();
                        value = currentLine.replace("<td>", "").replace("</td>", "")
                                .replaceAll("^\s*", "");
                        currentLine = fileInput.nextLine();
                        serial = currentLine.replace("<td>", "").replace("</td>", "")
                                .replaceAll("^\s*", "");

                        //adds each next value to the inventory as an item
                        Item.Inventory.add(new Item(name, serial, Integer.parseInt(value)));
                    }
                    break;
                }
            }
            //if somehow the file doesnt open, prints invalid file.
        } catch (FileNotFoundException e) {
            System.out.println("Invalid File.");
        }
    }


    public Item[] openJSONFile(File file) {
        //clears inventory
        Item.getInventory().clear();
        //reads json file and passes back array of items
        try (Reader reader = new FileReader(file)){
            Gson gson = new Gson();
            return gson.fromJson(reader, Item[].class);
            //if somehow the file doesnt open, prints invalid file.
        } catch (IOException e) {
            System.out.println("Invalid File.");
        }


        return new Item[0];
    }


    public void openTSVFile(File file) {

        //clear inventory when starting
        Item.getInventory().clear();

        Item inventoryList = new Item();
        String fullLine;
        //similar to assignment 1, parse lines based on tabs and add them to an inventory
        try (Scanner input = new Scanner(file)) {
            input.nextLine();
            while (input.hasNextLine()) {
                fullLine = input.nextLine();
                String[] itemValues = fullLine.split("\t");
                Item.getInventory().add(new Item(itemValues[1], itemValues[0], Integer.parseInt(itemValues[2])));
            }
            //if somehow the file doesnt open, prints invalid file.
        } catch (Exception e) {
            System.out.println("Invalid File");
        }
    }


    public void saveHTMLFile(File file, List<Item> itemList)
    {
        StringBuilder output = new StringBuilder();

        output.append("""
                <!DOCTYPE html>
                <html>
                <body>
                <style>
                html {
                  font-family: serif;
                }
                                
                table {
                  border-collapse: collapse;
                  border: 1px solid rgb(200,200,200);
                  letter-spacing: 1px;
                  font-size: 1.0rem;
                }
                                
                td, th {
                  border: 1px solid rgb(200,200,200);
                  padding: 10px 10px;
                }
                                
                td {
                  text-align: center;
                }
             
                </style>
                                
                 <table>
                  <tr>
                    <th>Name</th>
                    <th>Value</th>
                    <th>Serial Number</th>
                  </tr>
                  """);

        for (Item item : itemList) {
            output.append("  <tr>%n    <td>").append(item.getName()).append("</td>%n");
            output.append("    <td>").append(item.getValue()).append("</td>%n");
            output.append("    <td>").append(item.getSerialNumber()).append("</td>%n  </tr>%n");
        }

        output.append("""
                </table>\040
                                
                </body>
                </html>""");

        try(Formatter fileFormat = new Formatter(file.toString())){
            fileFormat.format(output.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
                output.format("%s\t%s\t%d%n", item.getSerialNumber(), item.getName(), item.getValue());
            }
            //if for some reason it doesnt work, note error
        } catch (IOException e) {
            System.out.println("Error making file");
        }
    }



}
