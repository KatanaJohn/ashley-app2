package baseline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FXMLControllerTest {

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
