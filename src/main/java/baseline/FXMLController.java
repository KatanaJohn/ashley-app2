package baseline;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

public class FXMLController {

    @FXML
    private Button addButton;

    @FXML
    private Button deleteAllButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button editButton;

    @FXML
    private Menu fileButton;

    @FXML
    private Menu searchButton;

    @FXML
    private MenuBar menuBar;

    @FXML
    private TableColumn<Item, String> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private MenuItem openButton;

    @FXML
    private MenuItem saveButton;

    @FXML
    private MenuItem searchButton2;

    @FXML
    private TableColumn<Item, String> serialColumn;

    @FXML
    private TextField serialField;

    @FXML
    private MenuItem serialSearchButton;

    @FXML
    private TableView<Item> tableOfItems;

    @FXML
    private TableColumn<Item, Integer> valueColumn;

    @FXML
    private TextField valueField;

    @FXML
    void initialize()
    {
        //set table cell for description
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        //set table cell for
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        //set table cell for  due dates
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));


    }

    private int index = 0;
    private final WarningChecker checker = new WarningChecker();


    @FXML
    void addItem(ActionEvent event) {

        //creates a new inventory item
        System.out.println("Hi this works");
        Item item = new Item();

        String currentName = nameField.getText();
        String currentSerial = serialField.getText();

        //insert check warning for name

        int currentValue;
        try
        {
            currentValue = Integer.parseInt(valueField.getText());
        }
        catch(NullPointerException e)
        {
            currentValue = 0;
        }

        //try catch for this

        //insert check warning for value

        if(!checker.checkName(currentName))
        {
            System.out.println("Name is wrong.");
        }
        else if(!checker.checkSerial(currentSerial) || !checkSameSerial(currentSerial))
        {
            System.out.println("Serial is wrong.");
        }
        else if(!checker.checkValue(currentValue))
        {
            System.out.println("Value is wrong");
        }
        else
        {
            item.setName(currentName);
            item.setSerialNumber(currentSerial);
            item.setValue(currentValue);
            //adds item to inventory and table
            System.out.println("Made it to end");
            tableOfItems.getItems().add(item);
            Item.Inventory.add(item);
        }




        //clears all item boxes
        nameField.clear();
        valueField.clear();
        serialField.clear();

    }

    public boolean checkSameSerial(String serial)
    {
        for(Item i : Item.getInventory())
        {
            if(i.getSerialNumber().equals(serial))
            {
                return false;
            }
        }
        return true;
    }

    @FXML
    void deleteAll(ActionEvent event) {

        //deletes entire inventory and table
        Item.getInventory().clear();
        tableOfItems.getItems().clear();
    }

    @FXML
    void deleteItem(ActionEvent event) {

        //get selected row and get all the items within the list
        Item selectedItem = tableOfItems.getSelectionModel().getSelectedItem();
        ObservableList<Item> itemSelected;
        ObservableList<Item> allItems;
        allItems = tableOfItems.getItems();
        itemSelected = tableOfItems.getSelectionModel().getSelectedItems();

        //remove the item from the display and todolist
        Item.Inventory.remove(selectedItem);
        itemSelected.forEach(allItems::remove);
    }

    @FXML
    void editItem(ActionEvent event) {

        //very similar to add item
        int idx = tableOfItems.getSelectionModel().getSelectedIndex();

        Item item = new Item();

        String currentName = nameField.getText();
        String currentSerial = serialField.getText();

        //insert check warning for name

        int currentValue;
        try
        {
            currentValue = Integer.parseInt(valueField.getText());
        }
        catch(NullPointerException e)
        {
            currentValue = 0;
        }

        //try catch for this

        //insert check warning for value

        if(!checker.checkName(currentName))
        {
            System.out.println("Name is wrong.");
        }
        else if(!checker.checkSerial(currentSerial) || !checkSameSerial(currentSerial))
        {
            System.out.println("Serial is wrong.");
        }
        else if(!checker.checkValue(currentValue))
        {
            System.out.println("Value is wrong");
        }
        else
        {
            item.setName(currentName);
            item.setSerialNumber(currentSerial);
            item.setValue(currentValue);
            //adds item to inventory and table
            System.out.println("Made it to end");
            tableOfItems.getItems().set(idx, item);
            Item.Inventory.set(idx, item);
        }


    }

    @FXML
    void openInventoryFile(ActionEvent event) {

        //uses filechooser and opens window for user to choose file

        //calls import list function

        //reads through the list using buffered reader and splices it based on file type
    }

    @FXML
    void saveInventoryFile(ActionEvent event) {

        //uses filechooser to save file

        //depending on the file type, calls a certain function to save in that file format

    }
    @FXML
    void loadSearchMenu(ActionEvent event) throws Exception
    {
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("searchMenuGui.fxml")));
        Stage stage = new Stage();
        //set what you want on your stage
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Report Page");
        stage.setScene(new Scene(root1));
        stage.setResizable(false);
        stage.show();
    }

    public void getInventoryHTML(File selectedFile)
    {
        //honestly not sure about this one
    }

    public void getInventoryJSON(File selectedFile)
    {
        //honestly not sure about this one
    }

    public void getInventoryTSV(File selectedFile)
    {
        //writes information to file separating the 3 table rows with tabs
    }






    void filterList()
    {

    }


}
