package baseline;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;

import java.io.File;

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
    private Menu menu;

    @FXML
    private Menu menu1;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem menuSortNameButton;

    @FXML
    private MenuItem menuSortSerial;

    @FXML
    private MenuItem menuSortValueButton;

    @FXML
    private MenuItem nameSearchButton;

    @FXML
    private MenuItem openButton;

    @FXML
    private MenuItem saveButton;

    @FXML
    private MenuItem serialSearchButton;

    @FXML
    private TableView<?> tableOfItems;

    @FXML
    private Menu viewListButton;

    @FXML
    void addItem(ActionEvent event) {

        //creates a new inventory item

        //does all the checks for the input info

        //adds item to list and table

        //clears all item boxes

    }

    @FXML
    void deleteAll(ActionEvent event) {

        //deletes entire inventory and table
    }

    @FXML
    void deleteItem(ActionEvent event) {

        //takes the selected table row

        //makes a list of all items and removes selected item from the table and list
    }

    @FXML
    void editItem(ActionEvent event) {

        //very similar to add item

        //creates new inventory item

        //does all the checks for correct input info


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

    @FXML
    void sortByName(ActionEvent event) {

        //table possibly sorts automatically?
    }

    @FXML
    void sortBySerialNumber(ActionEvent event) {

        //table possibly sorts automatically?
    }

    @FXML
    void sortByValue(ActionEvent event) {

        //table possibly sorts automatically?
    }

    @FXML
    void searchByName(ActionEvent event) {

        //takes name from search box

        //checks the inventory for matching name and shows results in table

    }

    @FXML
    void searchBySerial(ActionEvent event) {

        //takes serial from search box

        //checks the inventory for matching serial numbers and displays it

    }

}
