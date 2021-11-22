package baseline;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FXMLControllerTest {



    @Test
    void addMinimumItems()
    {
        //clear inventory
        Item.getInventory().clear();
        //random
        Random r = new Random();
        //string of characters and numbers we'll randomize from
        String characters = "abcdefghijklmnopqrstuvwxyz1234567890";

        //generator 1024 serial numbers
        for(int i = 0; i < 1024; i++)
        {
            StringBuilder string = new StringBuilder();
            //generate a serial number using string builder
            for(int j = 0; j < 11; j++)
            {

                //places - in the correct spots
                if(j == 3 || j == 7)
                {
                    string.append("-");
                }
                //generates and adds a random character/number to the stringbuilder
                else
                {
                    string.append(characters.charAt(r.nextInt(characters.length())));
                }
            }
            //creates the new item
            Item item = new Item("test", "A-" + string.toString(), 555);
            //adds it to inventory
            Item.getInventory().add(i, item);
        }

        //this is just to display that we successfully randomized a serial number. Chances of generating the same serial are extremely low.
        System.out.println(Item.Inventory.get(0).serialNumber);
        //checks if we have at least 1024 items
        assertTrue(Item.Inventory.size() > 1023);
    }


    @Test
    void addItemTest()
    {
        Item.getInventory().clear();
        Item item = new Item();
        String currentName = "nametest";
        String currentSerial = "A-500-b40-300";
        int currentValue = 500;

        item.setName(currentName);
        item.setSerialNumber(currentSerial);
        item.setValue(currentValue);
        Item.Inventory.add(item);

        assertEquals(Item.Inventory.get(0).name, "nametest");
    }

    @Test
    void checkSameSerialTest()
    {
        Item item = new Item("nametest", "a-400-c32-200", 500);
        Item item2 = new Item("nametest2", "A-500-b40-300", 350);
        String serial = "A-500-b40-300";
        Item.Inventory.add(item);
        Item.Inventory.add(item2);
        boolean check = true;
        for(Item i : Item.getInventory())
        {
            //if the serial numbers match, sends back false boolean to trigger error
            if(i.getSerialNumber().equals(serial))
            {
                check = false;
            }
        }

        assertFalse(check);

    }

    @Test
    void deleteAll()
    {
        Item item = new Item("nametest", "a-400-c32-200", 500);
        Item item2 = new Item("nametest2", "A-500-b40-300", 350);
        Item.getInventory().clear();
        Item item3 = new Item("nametest3", "A-200-b40-300", 450);
        Item.Inventory.add(item3);
        //since the first 2 items are deleted, the 1st item should be item3
        assertEquals(Item.getInventory().get(0).name, "nametest3");
    }

    @Test
    void deleteItem()
    {
        Item.getInventory().clear();
        Item item = new Item("nametest", "a-400-c32-200", 500);
        Item item2 = new Item("nametest2", "A-500-b40-300", 350);
        Item.Inventory.add(item);
        Item.Inventory.add(item2);
        Item.Inventory.remove(item);
        assertEquals(Item.getInventory().get(0).name, "nametest2");
    }

    @Test
    void editItem()
    {
        Item.getInventory().clear();
        Item item2 = new Item("nametest2", "A-500-b40-300", 350);
        Item.Inventory.add(item2);

        Item item = new Item();
        String currentName = "nametest";
        String currentSerial = "A-500-b40-300";
        int currentValue = 500;

        item.setName(currentName);
        item.setSerialNumber(currentSerial);
        item.setValue(currentValue);
        Item.Inventory.set(0, item);

        assertEquals(Item.Inventory.get(0).name, "nametest");
    }




}
