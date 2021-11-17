package baseline;


import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {

    @Test
    void createItem()
    {
        Item item = new Item("John", "a-444-444-444", 1000000);

        assertTrue(Objects.equals(item.name, "John")
                && Objects.equals(item.serialNumber, "a-444-444-444")
                && Objects.equals(item.value, 1000000));
    }

    @Test
    void getName()
    {
        Item item = new Item("John", "a-444-444-444", 1000000);

        assertEquals(item.getName(), "John");
    }

    @Test
    void setName()
    {
        Item item = new Item("John", "a-444-444-444", 1000000);
        item.setName("Juan");
        assertEquals("Juan", item.getName());
    }

    @Test
    void getSerialNumber()
    {
        Item item = new Item("John", "a-444-444-444", 1000000);

        assertEquals(item.getSerialNumber(), "a-444-444-444");
    }

    @Test
    void setSerialNumber()
    {
        Item item = new Item("John", "a-444-444-444", 1000000);
        item.setSerialNumber("c-100-100-100");
        assertEquals("c-100-100-100", item.getSerialNumber());
    }

    @Test
    void getValue()
    {
        Item item = new Item("John", "a-444-444-444", 1000000);

        assertEquals(1000000, item.getValue());
    }

    @Test
    void setValue()
    {
        Item item = new Item("John", "a-444-444-444", 1000000);
        item.setValue(1);
        assertEquals(1, item.getValue());
    }

    @Test
    void getInventory()
    {
        Item.getInventory().clear();
        Item item = new Item("John", "a-444-444-444", 1000000);
        item.Inventory.add(item);
        item = new Item("Jason", "a-111-111-111", 1);

        int i = 0;
        for(Item itm : Item.getInventory())
        {
            assertEquals(item.Inventory.get(i).toString(), itm.toString());
        }
    }
}
