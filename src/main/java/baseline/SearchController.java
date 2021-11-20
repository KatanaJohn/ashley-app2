/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 John Ashley
 */


package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchController {


    @FXML
    private TableColumn<Item, String> nameColumn;

    @FXML
    private TextField nameField;

    @FXML
    private Button nameSearchButton;

    @FXML
    private TableColumn<Item, String> serialColumn;

    @FXML
    private TextField serialField;

    @FXML
    private Button serialSearchButton;

    @FXML
    private TableView<Item> tableOfItems;

    @FXML
    private TableColumn<Item, Integer> valueColumn;

    @FXML
    void initialize()
    {
        //set table cell for description
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        //set table cell for value
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        //set table cell for  due dates
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));


    }

     @FXML
    void searchByName(ActionEvent event) {

        //create an observable list to add items to
         ObservableList<Item> items = FXCollections.observableArrayList();
        String nameSearch = nameField.getText();

        //checks the inventory for matching name and shows results in table
        for (Item item : Item.getInventory())
        {
            if(item.getName().equals(nameSearch))
            {
                items.add(item);
            }
        }
        //display the items that match
        displayItem(items);

    }

    @FXML
    void searchBySerial(ActionEvent event) {

        //create an observable list to add items to
        ObservableList<Item> items = FXCollections.observableArrayList();
        //takes serial from search box
        String serialSearch = serialField.getText();

        //checks the inventory for matching serial numbers and displays it
        for (Item item : Item.getInventory())
        {
            if(item.getSerialNumber().equals(serialSearch))
            {
                items.add(item);
            }
        }
        displayItem(items);
    }


    void displayItem(ObservableList<Item> items)
    {
        //clears table and displays new set of items (in the case the user does multiple searches)
        tableOfItems.getItems().clear();
        tableOfItems.refresh();
        tableOfItems.setItems(items);
    }




}
