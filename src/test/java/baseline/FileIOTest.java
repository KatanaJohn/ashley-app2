package baseline;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Formatter;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileIOTest {




    @Test
    void saveAndOpenJSONFile() throws IOException {
        //clearing inventory and adding everything
        Item.Inventory.clear();
        Gson gson = new Gson();
        Item item = new Item("nametest", "a-400-c32-200", 500);
        Item item2 = new Item("nametest2", "A-500-b40-300", 350);
        Item.Inventory.add(item);
        Item.Inventory.add(item2);

        //creates temp file
        File file = File.createTempFile("tempFile", ".gson");


        //save the file
        try {
            Writer writer = new FileWriter(file);
            gson.toJson(Item.getInventory(), writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //asserting the file exists
        assertTrue(file.exists());

        //this asserts that the file saved contains the correct information when we "open" it here.
        try (Reader reader = new FileReader(file)) {
            Item[] newList = gson.fromJson(reader, Item[].class);
            int idx = 0;
            for (Item value : newList) {

                assertEquals(value.getName(), Item.getInventory().get(idx).name);
                idx++;
            }

        }
    }


    @Test
    void saveAndOpenTSVFile() throws IOException {
        Item.Inventory.clear();
        Item item1 = new Item("nametest", "a-400-c32-200", 500);
        Item item2 = new Item("nametest2", "A-500-b40-300", 350);
        Item.Inventory.add(item1);
        Item.Inventory.add(item2);

        //creates temp file
        File file = File.createTempFile("tempFile", ".txt");

        //add the items to the file the same way we did for the actual function
        try (Formatter output = new Formatter(file)) {
            output.format("Name\tValue\tSerial Number%n");
            for (Item item : Item.getInventory()) {
                output.format("%s\t%d\t%s%n", item.getName(), item.getValue(), item.getSerialNumber());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //check if the file exists
        assertTrue(file.exists());



        //to test the file being saved correctly, all the items will be added into the inventory again in the same order
        String nextLine;
        try (Scanner input = new Scanner(file)) {
            input.nextLine();
            while (input.hasNextLine()) {
                nextLine = input.nextLine();
                String[] itemValues = nextLine.split("\t");
                Item.getInventory().add(new Item(itemValues[0], itemValues[2], Integer.parseInt(itemValues[1])));
            }
            //if somehow the file doesnt open, prints invalid file.
        } catch (Exception e) {
            e.printStackTrace();
        }

        //set the first index to 0 and set the second index at the halfway point of the inventory
        int idx1 = 0;
        int idx2 = Item.Inventory.size() / 2;


        //the inventory should have 4 items and the 3rd/4th item should be the same as the 1st/2nd
        //in most cases we'd clear the inventory when opening, but we're only checking if the items are the same
        while(idx2 < Item.Inventory.size())
        {
            assertEquals(Item.getInventory().get(idx1).name, Item.getInventory().get(idx2).name);
            assertEquals(Item.getInventory().get(idx1).value, Item.getInventory().get(idx2).value);
            assertEquals(Item.getInventory().get(idx1).serialNumber, Item.getInventory().get(idx2).serialNumber);
            idx1++;
            idx2++;
        }

    }

    @Test
    void saveAndOpenHTMLFile() throws IOException {
        Item.Inventory.clear();
        Gson gson = new Gson();
        Item item1 = new Item("nametest", "a-400-c32-200", 500);
        Item item2 = new Item("nametest2", "A-500-b40-300", 350);
        Item.Inventory.add(item1);
        Item.Inventory.add(item2);

        File file = File.createTempFile("tempFile", ".html");

        //saving html file. same as the original function
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

        //for each item in the list, get the name/value/serial and output them
        for (Item item : Item.getInventory()) {
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

        //assert file exists
        assertTrue(file.exists());

        //open the file same as the actual function
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

                        Item.Inventory.add(new Item(name, serial, Integer.parseInt(value)));
                    }
                    break;
                }
            }
            //if somehow the file doesnt open, prints error
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //set the first index to 0 and set the second index at the halfway point of the inventory
        int idx1 = 0;
        int idx2 = Item.Inventory.size() / 2;


        //the inventory should have 4 items and the 3rd/4th item should be the same as the 1st/2nd
        //in most cases we'd clear the inventory when opening, but we're only checking if the items are the same
        while(idx2 < Item.Inventory.size())
        {
            assertEquals(Item.getInventory().get(idx1).name, Item.getInventory().get(idx2).name);
            assertEquals(Item.getInventory().get(idx1).value, Item.getInventory().get(idx2).value);
            assertEquals(Item.getInventory().get(idx1).serialNumber, Item.getInventory().get(idx2).serialNumber);
            idx1++;
            idx2++;
        }

    }


}
