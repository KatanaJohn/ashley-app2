/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 John Ashley
 */

package baseline;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    String alertSignal = "Error Alert";

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
        Item item = new Item();

        //gets the values from the text fields
        String currentName = nameField.getText();
        String currentSerial = serialField.getText();
        int currentValue;
        try
        {
            currentValue = Integer.parseInt(valueField.getText());
        }
        catch(Exception e)
        {
            currentValue = -1;
        }


        //calls checkName from checker function. If not passed, sends error
        if(!checker.checkName(currentName))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(alertSignal);
            alert.setContentText("Your item name is not between 2 and 256 characters in length.");
            alert.showAndWait();
        }
        //calls checkSerial from checker function. If not passed, sends error
        else if(!checker.checkSerial(currentSerial) || !checkSameSerial(currentSerial))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(alertSignal);
            alert.setContentText("Your serial number was not input correctly. It should be in the format of A-XXX-XXX-XXX, where a is a letter and x is a letter/digit.");
            alert.showAndWait();
        }
        //calls checkValue from checker function. If not passed, sends error
        else if(!checker.checkValue(currentValue))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(alertSignal);
            alert.setContentText("Your value is set to a number less than 0. It must be greater than or equal to 0.");
            alert.showAndWait();
        }
        //if all checks are passed, sets all the values and adds them
        else
        {
            item.setName(currentName);
            item.setSerialNumber(currentSerial);
            item.setValue(currentValue);
            //adds item to inventory and table
            tableOfItems.getItems().add(item);
            Item.Inventory.add(item);
        }




        //clears all item boxes
        nameField.clear();
        valueField.clear();
        serialField.clear();

    }

    private boolean checkSameSerial(String serial)
    {
        //for each item in the inventory, checks if the serial number matches the serial of the item we're trying to add
        for(Item i : Item.getInventory())
        {
            //if the serial numbers match, sends back false boolean to trigger error
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
        //get values from name field
        String currentName = nameField.getText();
        String currentSerial = serialField.getText();

        //insert check warning for name

        int currentValue;
        try
        {
            currentValue = Integer.parseInt(valueField.getText());
        }
        catch(Exception e)
        {
            currentValue = -1;
        }

        //try catch for this

        //calls checkName from checker function. If not passed, sends error
        if(!checker.checkName(currentName))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(alertSignal);
            alert.setContentText("Your item name is not between 2 and 256 characters in length.");
            alert.showAndWait();
        }
        //calls checkSerial from checker function. If not passed, sends error
        else if(!checker.checkSerial(currentSerial) || !checkSameSerial(currentSerial))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(alertSignal);
            alert.setContentText("Your serial number was not input correctly. It should be in the format of A-XXX-XXX-XXX, where a is a letter and x is a letter/digit.");
            alert.showAndWait();
        }
        //calls checkValue from checker function. If not passed, sends error
        else if(!checker.checkValue(currentValue))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(alertSignal);
            alert.setContentText("Your value is set to a number less than 0. It must be greater than or equal to 0.");
            alert.showAndWait();
        }
        else
        {
            //if all the checks are passed, the item values are set
            item.setName(currentName);
            item.setSerialNumber(currentSerial);
            item.setValue(currentValue);
            //adds item to inventory and table by replacing the selected index
            tableOfItems.getItems().set(idx, item);
            Item.Inventory.set(idx, item);
        }

        //clears all the fields
        nameField.clear();
        valueField.clear();
        serialField.clear();
    }

    private void displayList()
    {
        //set table cell for description
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        //set table cell for
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        //set table cell for  due dates
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));

        //gets the items from the inventory and adds them to the table
        tableOfItems.getItems().add(Item.getInventory().get(index));
        //increase index by 1
        index++;
    }

    @FXML
    void openInventoryFile(ActionEvent event) throws FileNotFoundException {

        String html = "*.html";
        String gson = "*.gson";
        String tsv = "*.txt";

        //check if table is null and clear if not
        if(this.tableOfItems!= null){
            tableOfItems.getItems().clear();
        }
        //set index to 0 for display function
        index = 0;

        //use a file chooser, create fileIO object, set the title, add all extensions, and open file dialog
        FileChooser fileChooser = new FileChooser();
        FileIO fileIO = new FileIO();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("HTML File", html),
                new FileChooser.ExtensionFilter("GSON File", gson),
                new FileChooser.ExtensionFilter("TSV File", tsv));

        File file = fileChooser.showOpenDialog(tableOfItems.getScene().getWindow());


        //when the user selects a file, go through the function that matches the extension
        if (file != null) {
            if (fileChooser.getSelectedExtensionFilter().getExtensions().get(0).equals(html)) {
               fileIO.openHTMLFile(file);
            } else if (fileChooser.getSelectedExtensionFilter().getExtensions().get(0).equals(gson)) {
                Item[] itemList = fileIO.openJSONFile(file);
                //for gson specifically, for each item it gets the values and adds them back to the list
                for (Item value : itemList) {
                    Item item = new Item(value.getName(), value.getSerialNumber(), value.getValue());
                    Item.getInventory().add(item);
                }

            } else {
                fileIO.openTSVFile(file);
            }
        }

        //after remaking the inventory list, display all items in table
        for(int i = 0; i < Item.getInventory().size(); i++)
        {
            displayList();
        }

        }

    @FXML
    void saveInventoryFile(ActionEvent event) {

        String html = "*.html";
        String gson = "*.gson";
        String tsv = "*.txt";

        //takes file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a file to save");
        fileChooser.setTitle("Save File");
        //adds exxtension filters/choices on ways to save
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("HTML File", html),
                new FileChooser.ExtensionFilter("GSON File", gson),
                new FileChooser.ExtensionFilter("TSV File", tsv));

        File file = fileChooser.showSaveDialog(tableOfItems.getScene().getWindow());

        //creates new FileIO so when we call it we save based on it
        FileIO fileIO = new FileIO();
        //based on the file type chosen, we save the inventory inside a file
        if (file != null) {
            if (fileChooser.getSelectedExtensionFilter().getExtensions().get(0).equals(html)) {
                fileIO.saveHTMLFile(file, Item.getInventory());
            } else if (fileChooser.getSelectedExtensionFilter().getExtensions().get(0).equals(gson)) {
                fileIO.saveJSONFile(file, Item.getInventory());
            } else if (fileChooser.getSelectedExtensionFilter().getExtensions().get(0).equals(tsv)) {
                fileIO.saveTSVFile(file, Item.getInventory());
            }
            //if somehow a file not related to any of the types is chosen, sends alert
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(alertSignal);
                alert.setContentText("The file you chose is not valid.");
                alert.showAndWait();
            }
        }
    }






    //this function loads a search menu fxml file for search functions (SearchController)
    @FXML
    void loadSearchMenu(ActionEvent event) throws IOException
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


}
