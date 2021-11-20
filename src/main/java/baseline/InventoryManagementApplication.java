/*
 *  UCF COP3330 Fall 2021 Application Assignment 2 Solution
 *  Copyright 2021 John Ashley
 */

package baseline;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class InventoryManagementApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("applicationassignment2_gui.fxml")));
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setTitle("Inventory");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args)
    {
        launch(args);
    }



}
