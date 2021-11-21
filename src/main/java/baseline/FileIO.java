/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 John Ashley
 */


package baseline;

import com.google.gson.Gson;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.*;

public class FileIO {



    //alert made in the case a file cant be opened
    private void alertFileOpen()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("File Error");
        alert.setContentText("The file you tried to open could not be opened.");
        alert.showAndWait();
    }

    //alert made in the case a file cant be saved
    private void alertFileSave()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("File Error");
        alert.setContentText("The file you tried to save could not be saved.");
        alert.showAndWait();
    }


    public void openHTMLFile(File file) {
        //clears inventory
        Item.getInventory().clear();

        //makes strings for values

        try (Scanner fileInput = new Scanner(file)) {

            while (true) {
                String nextLine = fileInput.nextLine();

                if (nextLine.equals("    <th>Serial Number</th>")) {

                    while (true) {
                        fileInput.nextLine();

                        nextLine = fileInput.nextLine();
                        if (nextLine.equals("</table> ")) {
                            break;
                        }

                        //takes the next line and  gets the name, value, and serial number
                        nextLine = fileInput.nextLine();
                        String replacement = "</td>";
                        String name = nextLine.replace("<td>", "").replace(replacement, "")
                                .replaceAll("^\s*", "");
                        nextLine = fileInput.nextLine();
                        String value = nextLine.replace("<td>", "").replace(replacement, "")
                                .replaceAll("^\s*", "");
                        nextLine = fileInput.nextLine();
                        String serial = nextLine.replace("<td>", "").replace(replacement, "")
                                .replaceAll("^\s*", "");

                        //adds each next value to the inventory as an item
                        Item.Inventory.add(new Item(name, serial, Integer.parseInt(value)));
                    }
                    break;
                }
            }
            //if somehow the file doesnt open, prints error
        } catch (FileNotFoundException e) {
            alertFileOpen();
        }
    }


    public Item[] openJSONFile(File file) {
        //clears inventory
        Item.getInventory().clear();
        //reads json file and passes back array of items that were in the file
        try (Reader reader = new FileReader(file)){
            Gson gson = new Gson();
            return gson.fromJson(reader, Item[].class);
            //if somehow the file doesnt open, prints invalid file.
        } catch (IOException e) {
            alertFileOpen();
        }


        return new Item[0];
    }


    public void openTSVFile(File file) {

        //clear inventory when starting
        Item.getInventory().clear();

        String nextLine;
        //similar to assignment 1, parse lines based on tabs and add them to an inventory until end of file
        try (Scanner input = new Scanner(file)) {
            input.nextLine();
            while (input.hasNextLine()) {
                //get the next line of values
                nextLine = input.nextLine();
                //make an array of strings and parse the nextLine string based on tabs
                String[] itemValues = nextLine.split("\t");
                //add item values of the array to the inventory
                Item.getInventory().add(new Item(itemValues[0], itemValues[2], Integer.parseInt(itemValues[1])));
            }
            //if somehow the file doesnt open, prints invalid file.
        } catch (Exception e) {
            alertFileOpen();
        }
    }


    public void saveHTMLFile(File file, List<Item> itemList)
    {
        StringBuilder output = new StringBuilder();

        //html formatting into a table
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
                  border: 2px solid rgb(200,200,200);
                  letter-spacing: 1px;
                  font-size: 1.0rem;
                }
                                
                td, th {
                  border: 2px solid rgb(200,200,200);
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

        //for each item in the list, get the name/value/serial and output them
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
            alertFileSave();
        }
    }


    public void saveJSONFile(File file, List<Item> itemList) {
        Gson gson = new Gson();
        //write gson file by converting inventory using gson.toJson
        try {
            Writer writer = new FileWriter(file);
            gson.toJson(itemList,writer);
            writer.close();
            //if for some reason it doesnt work, note error
        } catch (IOException e) {

            alertFileSave();
        }

    }


    public void saveTSVFile(File file, List<Item> itemList) {
        //format txt file in a way to include spacing using \t between name, value, and serial
        try (Formatter output = new Formatter(file)) {
            output.format("Name\tValue\tSerial Number%n");
            //for each item in the list, format/print it to file
            for (Item item : itemList) {
                output.format("%s\t%d\t%s%n", item.getName(), item.getValue(), item.getSerialNumber());
            }
            //if for some reason it doesnt work, note error
        } catch (IOException e) {
            alertFileSave();
        }
    }



}
