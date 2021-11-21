package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchControllerTest {

    @Test
    void searchByNameTest()
    {
        ObservableList<Item> items = FXCollections.observableArrayList();
        Item item = new Item("nametest", "a-400-c32-200", 500);
        Item item2 = new Item("nametest2", "A-500-b40-300", 350);
        Item.getInventory().add(item);
        Item.getInventory().add(item2);
        String nameSearch = "nametest";

        for (Item itemCheck : Item.getInventory())
        {
            if(itemCheck.getName().equals(nameSearch))
            {
                items.add(itemCheck);
            }
        }

        assertEquals(items.get(0).name, nameSearch);
    }

    @Test
    void searchBySerialTest()
    {
        ObservableList<Item> items = FXCollections.observableArrayList();
        Item item = new Item("nametest", "a-400-c32-200", 500);
        Item item2 = new Item("nametest2", "A-500-b40-300", 350);
        Item.getInventory().add(item);
        Item.getInventory().add(item2);
        String serialSearch = "A-500-b40-300";

        for (Item itemCheck : Item.getInventory())
        {
            if(itemCheck.getSerialNumber().equals(serialSearch))
            {
                items.add(itemCheck);
            }
        }

        assertEquals(items.get(0).serialNumber, serialSearch);

    }


}
